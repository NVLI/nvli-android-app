package com.gov.iitnvli.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.DashboardDataModel;
import com.gov.iitnvli.datamodel.DetailsDataModel;
import com.gov.iitnvli.global.ActivityConstant;
import com.gov.iitnvli.global.AppConstants;
import com.gov.iitnvli.home.books.FragmentBookDetail;
import com.gov.iitnvli.home.general.FragmentGeneral;
import com.gov.iitnvli.home.general.FragmentGeneralDetail;
import com.gov.iitnvli.home.search.FragmentSearch;
import com.gov.iitnvli.home.thesis.FragmentThesisDetail;
import com.gov.iitnvli.httpcommunication.httpmanager.HttpRequestManager;
import com.gov.iitnvli.httpcommunication.httpmanager.RequestType;
import com.gov.iitnvli.httpcommunication.httpmanager.ResponseHandler;


public class LandingActivity extends AppCompatActivity implements MaterialViewPager.Listener, ResponseHandler {

    //    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private ImageView headerLogo;
    private int pageCount = 5;
    private HttpRequestManager httpRequestManager;
//    private ListView drawerList;
//    String[] testValues = new String[]{"Home", "News", "Settings", "Logout"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

//        setupDrawer();
        setTitle("");
        headerLogo = (ImageView) findViewById(R.id.headerLogo);
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        hideHomeButton();
        httpRequestManager = new HttpRequestManager(this, this);
        httpRequestManager.getDashboardList("0", "5");
    }

    private void hideHomeButton() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }
    }

    private void setupMaterialViewPager(DashboardDataModel dashboardDataModel) {
        mViewPager.getViewPager().setAdapter(new CustomViewPagerAdapter(getSupportFragmentManager(), dashboardDataModel));
        mViewPager.setMaterialViewPagerListener(this);
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }

    /*private void setupDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        drawer.setDrawerListener(drawerToggle);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        drawerList = (ListView) findViewById(R.id.menuList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, testValues);
        drawerList.setAdapter(adapter);
    }*/

   /* @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }*/

    @Override
    public HeaderDesign getHeaderDesign(int page) {
        AppConstants.currentTabIdx = page;
        switch (page) {
            case 0:
                headerLogo.setImageResource(R.drawable.book);
                return HeaderDesign.fromColorResAndDrawable(
                        R.color.books,
                        getResources().getDrawable(R.drawable.books_banner));
            case 1:
                headerLogo.setImageResource(R.drawable.thesis);
                return HeaderDesign.fromColorResAndDrawable(
                        R.color.thesis,
                        getResources().getDrawable(R.drawable.thesis_banner));
            case 2:
                headerLogo.setImageResource(R.drawable.musem);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.musem,
                        "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
            case 3:
                headerLogo.setImageResource(R.drawable.audiovideo);
                return HeaderDesign.fromColorResAndDrawable(
                        R.color.audioVideo,
                        getResources().getDrawable(R.drawable.thesis_banner));
            case 4:
                headerLogo.setImageResource(R.drawable.newspaper);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.newspaper,
                        "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
            case 5:
                headerLogo.setImageResource(R.drawable.archieve);
                return HeaderDesign.fromColorResAndDrawable(
                        R.color.archieve,
                        getResources().getDrawable(R.drawable.books_banner));
            case 6:
                headerLogo.setImageResource(R.drawable.manuscript);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.manuscript,
                        "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
            case 7:
                headerLogo.setImageResource(R.drawable.map);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.map,
                        "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
        }
        //execute others actions if needed //
        return null;
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseObject == null) {
            return;
        }

        if (responseType.equals(RequestType.GET_DASHBOARD_LIST)) {
            try {
                setupMaterialViewPager((DashboardDataModel) responseObject);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class CustomViewPagerAdapter extends FragmentStatePagerAdapter {

        private DashboardDataModel dashboardDataModel;

        public CustomViewPagerAdapter(FragmentManager fm, DashboardDataModel dashboardDataModel) {
            super(fm);
            this.dashboardDataModel = dashboardDataModel;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position % pageCount) {
                case 0:
                    FragmentGeneral fBooks = new FragmentGeneral();
                    fBooks.setData(dashboardDataModel, ActivityConstant.BOOK_FRAGMENT);
                    return fBooks;
                case 1:
                    FragmentGeneral fThesis = new FragmentGeneral();
                    fThesis.setData(dashboardDataModel, ActivityConstant.THESIS_FRAGMENT);
                    return fThesis;
                case 2:
                    FragmentGeneral fMuseum = new FragmentGeneral();
                    fMuseum.setData(dashboardDataModel, ActivityConstant.MUSEM_FRAGMENT);
                    return fMuseum;
                case 3:
                    FragmentGeneral fVideo = new FragmentGeneral();
                    fVideo.setData(dashboardDataModel, ActivityConstant.VIDEO_FRAGMENT);
                    return fVideo;
                case 4:
                    FragmentGeneral fNewspaper = new FragmentGeneral();
                    fNewspaper.setData(dashboardDataModel, ActivityConstant.NEWSPAPER_FRAGMENT);
                    return fNewspaper;
                case 5:
                    FragmentGeneral fArchieves = new FragmentGeneral();
                    fArchieves.setData(dashboardDataModel, ActivityConstant.ARCHIEVES_FRAGMENT);
                    return fArchieves;
                case 6:
                    FragmentGeneral fManuscripts = new FragmentGeneral();
                    fManuscripts.setData(dashboardDataModel, ActivityConstant.MANUSCRIPT_FRAGMENT);
                    return fManuscripts;
                case 7:
                    FragmentGeneral fMaps = new FragmentGeneral();
                    fMaps.setData(dashboardDataModel, ActivityConstant.MAPS_FRAGMENT);
                    return fMaps;
                default:
                    FragmentGeneral fragmentGeneral = new FragmentGeneral();
                    fragmentGeneral.setData(dashboardDataModel, ActivityConstant.BOOK_FRAGMENT);
                    return fragmentGeneral;
            }
        }

        @Override
        public int getCount() {
            return pageCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position % pageCount) {
                case 0:
                    return getString(R.string.books);
                case 1:
                    return getString(R.string.thesis);
                case 2:
                    return getString(R.string.museum);
                case 3:
                    return getString(R.string.audiovideo);
                case 4:
                    return getString(R.string.newspaper);
                case 5:
                    return getString(R.string.archieves);
                case 6:
                    return getString(R.string.manuscripts);
                case 7:
                    return getString(R.string.maps);
            }
            return "";
        }
    }

    public void navigateTo(int position, Object obj, boolean state, Bundle bundle) {

        switch (position) {
            case ActivityConstant.BOOK_DETAIL_FRAGMENT: {
                FragmentBookDetail fragmentBookDetail = new FragmentBookDetail();
                fragmentBookDetail.setBookDetailData((DetailsDataModel) obj);
                changeFragment(fragmentBookDetail, state);
            }
            break;
            case ActivityConstant.THESISK_DETAIL_FRAGMENT: {
                FragmentThesisDetail fragmentThesisDetail = new FragmentThesisDetail();
                fragmentThesisDetail.setThesisDetailData((DetailsDataModel) obj);
                changeFragment(fragmentThesisDetail, state);
            }
            break;
            case ActivityConstant.SEARCH_FRAGMENT: {
                FragmentSearch fragmentSearch = new FragmentSearch();
                changeFragment(fragmentSearch, state);
            }
            break;
            case ActivityConstant.GENERAL_DETAIL_FRAGMENT: {
                FragmentGeneralDetail fragmentGeneralDetail = new FragmentGeneralDetail();
                fragmentGeneralDetail.setDetailData((DetailsDataModel) obj);
                changeFragment(fragmentGeneralDetail, state);
            }
            break;
            default:
                break;
        }
    }

    public void resetToHomeScreen() {
        Log.e("Reset", "Resetting to Homescreen");
        FragmentManager fManager = getSupportFragmentManager();
        fManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void changeFragment(Fragment fragmentToChange, boolean state) {
        try {
            hideKeyboard();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentView, fragmentToChange);
            if (state) {
                fragmentTransaction.addToBackStack(fragmentToChange.getClass().toString());
                Log.e("Add", fragmentToChange.getClass().toString());
            }
            fragmentTransaction.commit();
        } catch (Exception e) {

        }
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(getApplicationContext());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            navigateTo(ActivityConstant.SEARCH_FRAGMENT, null, true, null);
        }
        return super.onOptionsItemSelected(item);
    }
}
