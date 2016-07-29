package com.gov.iitnvli.home.general;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.DashboardDataModel;
import com.gov.iitnvli.datamodel.ListItemModel;
import com.gov.iitnvli.global.ActivityConstant;
import com.gov.iitnvli.home.LandingActivity;
import com.gov.iitnvli.home.ListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGeneral extends Fragment {

    private RecyclerView generalList;
    private ListRecyclerViewAdapter adapter;
    private View parentView;
    private LinearLayoutManager layoutManager;
    private LandingActivity activity;
    private DashboardDataModel dashboardDataModel;
    private int fragType;

    public FragmentGeneral() {
        // Required empty public constructor
    }

    public void setData(DashboardDataModel dashboardDataModel, int fragType) {
        this.dashboardDataModel = dashboardDataModel;
        this.fragType = fragType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (LandingActivity) getActivity();

        if (parentView != null) {
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_general, container, false);

        generalList = (RecyclerView) parentView.findViewById(R.id.generalList);
        layoutManager = new LinearLayoutManager(activity);
        generalList.setLayoutManager(layoutManager);
        generalList.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        adapter = new ListRecyclerViewAdapter(getListData(), activity, ActivityConstant.GENERAL_DETAIL_FRAGMENT);
        generalList.setAdapter(adapter);

        return parentView;
    }


    private ArrayList<ListItemModel> getListData() {

        switch (fragType) {
            case ActivityConstant.BOOK_FRAGMENT:
                return getBooksData(dashboardDataModel.getResult().getBooks());
            case ActivityConstant.THESIS_FRAGMENT:
                return getThesisData(dashboardDataModel.getResult().getJournal_and_thesis());
            case ActivityConstant.MUSEM_FRAGMENT:
                return getMuseumData(dashboardDataModel.getResult().getMuseum());
            case ActivityConstant.ARCHIEVES_FRAGMENT:
                return getArchievesData(dashboardDataModel.getResult().getGovt_archives());
            case ActivityConstant.VIDEO_FRAGMENT:
                return getVideoData(dashboardDataModel.getResult().getAudio_video());
            case ActivityConstant.MANUSCRIPT_FRAGMENT:
                return getManuscriptData(dashboardDataModel.getResult().getManuscripts());
            case ActivityConstant.NEWSPAPER_FRAGMENT:
                return getNewspaperData(dashboardDataModel.getResult().getNewspaper_archives());
            case ActivityConstant.MAPS_FRAGMENT:
                return getMapsData(dashboardDataModel.getResult().getMaps());
            default:
                return null;
        }
    }

    private ArrayList<ListItemModel> getBooksData(List<DashboardDataModel.ResultBean.BooksBean> booksBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < booksBeen.size(); i++) {
            String title = booksBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = booksBeen.get(i).getResource().getNode_title();
            String headerImage = "http://inanutshell.ca/wp-content/uploads/2016/01/Books-Banner.jpg";
            String image = booksBeen.get(i).getResource().getImage_url();
            String description = booksBeen.get(i).getMetadata().getDescription();
            String entityId = booksBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }

    private ArrayList<ListItemModel> getThesisData(List<DashboardDataModel.ResultBean.JournalAndThesisBean> thesisBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < thesisBeen.size(); i++) {
            String title = thesisBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = thesisBeen.get(i).getResource().getNode_title();
            String headerImage = "https://www.teachingenglish.org.uk/sites/teacheng/files/styles/large/public/images/class_journals_iStock_000021675732XSmall.jpg?itok=eRUojT6a";
            String image = thesisBeen.get(i).getResource().getImage_url();
            String description = thesisBeen.get(i).getMetadata().getDescription();
            String entityId = thesisBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }

    private ArrayList<ListItemModel> getMuseumData(List<DashboardDataModel.ResultBean.MuseumBean> museumBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < museumBeen.size(); i++) {
            String title = museumBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = museumBeen.get(i).getResource().getNode_title();
            String headerImage = "http://www.brooklynrail.org/article_image/image/300/germany_report_guggenheim_banners.jpg";
            String image = museumBeen.get(i).getResource().getImage_url();
            String description = museumBeen.get(i).getMetadata().getDescription();
            String entityId = museumBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }

    private ArrayList<ListItemModel> getArchievesData(List<DashboardDataModel.ResultBean.GovtArchivesBean> govtArchivesBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < govtArchivesBeen.size(); i++) {
            String title = govtArchivesBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = govtArchivesBeen.get(i).getResource().getNode_title();
            String headerImage = "http://blogs.mtu.edu/library/files/2009/07/mtu-archives1.jpg";
            String image = govtArchivesBeen.get(i).getResource().getImage_url();
            String description = govtArchivesBeen.get(i).getMetadata().getDescription();
            String entityId = govtArchivesBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }

    private ArrayList<ListItemModel> getVideoData(List<DashboardDataModel.ResultBean.AudioVideoBean> audioVideoBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < audioVideoBeen.size(); i++) {
            String title = audioVideoBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = audioVideoBeen.get(i).getResource().getNode_title();
            String headerImage = "http://www.kffmil.org/hp_wordpress/wp-content/uploads/2014/05/AV-Banner.jpeg";
            String image = audioVideoBeen.get(i).getResource().getImage_url();
            String description = audioVideoBeen.get(i).getMetadata().getDescription();
            String entityId = audioVideoBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }

    private ArrayList<ListItemModel> getManuscriptData(List<DashboardDataModel.ResultBean.ManuscriptsBean> manuscriptsBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < manuscriptsBeen.size(); i++) {
            String title = manuscriptsBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = manuscriptsBeen.get(i).getResource().getNode_title();
            String headerImage = "http://americanhistory.si.edu/sites/default/files/exhibitions/manuscript_header_0.jpg";
            String image = manuscriptsBeen.get(i).getResource().getImage_url();
            String description = manuscriptsBeen.get(i).getMetadata().getDescription();
            String entityId = manuscriptsBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }


    private ArrayList<ListItemModel> getNewspaperData(List<DashboardDataModel.ResultBean.NewspaperArchivesBean> newspaperArchivesBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < newspaperArchivesBeen.size(); i++) {
            String title = newspaperArchivesBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = newspaperArchivesBeen.get(i).getResource().getNode_title();
            String headerImage = "http://www.herefordroundtable.co.uk/wp-content/uploads/2015/12/newspaper-banner.jpg";
            String image = newspaperArchivesBeen.get(i).getResource().getImage_url();
            String description = newspaperArchivesBeen.get(i).getMetadata().getDescription();
            String entityId = newspaperArchivesBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }


    private ArrayList<ListItemModel> getMapsData(List<DashboardDataModel.ResultBean.MapsBean> mapsBeen) {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < mapsBeen.size(); i++) {
            String title = mapsBeen.get(i).getMetadata().getTitle_full();
            String nodeTitle = mapsBeen.get(i).getResource().getNode_title();
            String headerImage = "http://www.discovercomo.com/userfiles/images/map_banner(3).jpg";
            String image = mapsBeen.get(i).getResource().getImage_url();
            String description = mapsBeen.get(i).getMetadata().getDescription();
            String entityId = mapsBeen.get(i).getResource().getEntity_id();
            listItem.add(getItemModel(title, nodeTitle, headerImage, image, description, entityId));
        }
        return listItem;
    }

    private ListItemModel getItemModel(String title, String nodeTitle, String headerImage, String image, String description, String entityId) {
        ListItemModel listItemModel = new ListItemModel();

        if (title == null) {
            listItemModel.setTitle(nodeTitle);
        } else {
            listItemModel.setTitle(title);
        }

        if (image.isEmpty()) {
            listItemModel.setHeaderImage(headerImage);
            listItemModel.setImage(headerImage);
        } else {
            listItemModel.setHeaderImage(image);
            listItemModel.setImage(image);
        }

        if (description == null) {
            listItemModel.setDescription(nodeTitle);
        } else {
            listItemModel.setDescription(description);
        }
        listItemModel.setEntityId(entityId);
        return listItemModel;
    }
}
