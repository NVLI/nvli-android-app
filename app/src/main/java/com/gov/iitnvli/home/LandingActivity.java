package com.gov.iitnvli.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.gov.iitnvli.R;


public class LandingActivity extends AppCompatActivity implements MaterialViewPager.Listener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private ImageView headerLogo;
    private int pageCount = 8;
    private ListView drawerList;
    String[] testValues = new String[]{"Home", "News", "Settings", "Logout",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        setupDrawer();
        headerLogo = (ImageView) findViewById(R.id.headerLogo);
        setupMaterialViewPager();
    }

    private void setupMaterialViewPager() {
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(new CustomViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.setMaterialViewPagerListener(this);
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }

    private void setupDrawer() {
        setTitle("");
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
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public HeaderDesign getHeaderDesign(int page) {
        switch (page) {
            case 0:
                headerLogo.setImageResource(R.drawable.book);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.books,
                        "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
            case 1:
                headerLogo.setImageResource(R.drawable.thesis);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.thesis,
                        "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
            case 2:
                headerLogo.setImageResource(R.drawable.musem);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.musem,
                        "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
            case 3:
                headerLogo.setImageResource(R.drawable.archieve);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.archieve,
                        "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
            case 4:
                headerLogo.setImageResource(R.drawable.audiovideo);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.audioVideo,
                        "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
            case 5:
                headerLogo.setImageResource(R.drawable.manuscript);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.manuscript,
                        "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
            case 6:
                headerLogo.setImageResource(R.drawable.newspaper);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.newspaper,
                        "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
            case 7:
                headerLogo.setImageResource(R.drawable.map);
                return HeaderDesign.fromColorResAndUrl(
                        R.color.map,
                        "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
        }
        //execute others actions if needed //
        return null;
    }

    private class CustomViewPagerAdapter extends FragmentStatePagerAdapter {

        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position % pageCount) {
                //case 0:
                //    return RecyclerViewFragment.newInstance();
                //case 1:
                //    return RecyclerViewFragment.newInstance();
                //case 2:
                //    return WebViewFragment.newInstance();
                default:
                    return new FragmentBooks();
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
                    return getString(R.string.archieves);
                case 4:
                    return getString(R.string.audiovideo);
                case 5:
                    return getString(R.string.manuscripts);
                case 6:
                    return getString(R.string.newspaper);
                case 7:
                    return getString(R.string.maps);
            }
            return "";
        }
    }

}
