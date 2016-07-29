package com.gov.iitnvli.home.thesis;


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
import com.gov.iitnvli.global.ActivityConstant;
import com.gov.iitnvli.home.LandingActivity;
import com.gov.iitnvli.home.ListRecyclerViewAdapter;
import com.gov.iitnvli.datamodel.ListItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThesis extends Fragment {

    private RecyclerView thesisList;
    private ListRecyclerViewAdapter adapter;
    private View parentView;
    private LinearLayoutManager layoutManager;
    private LandingActivity activity;
    private List<DashboardDataModel.ResultBean.JournalAndThesisBean> thesisData;

    public FragmentThesis() {
        // Required empty public constructor
    }


    public void setThesisData(List<DashboardDataModel.ResultBean.JournalAndThesisBean> thesisData){
        this.thesisData = thesisData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (LandingActivity) getActivity();

        if (parentView != null) {
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_thesis, container, false);

        thesisList = (RecyclerView) parentView.findViewById(R.id.thesisList);
        layoutManager = new LinearLayoutManager(activity);
        thesisList.setLayoutManager(layoutManager);
        thesisList.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        adapter = new ListRecyclerViewAdapter(getThesisData(), activity, ActivityConstant.THESISK_DETAIL_FRAGMENT);
        thesisList.setAdapter(adapter);

        return parentView;
    }

    private ArrayList<ListItemModel> getThesisData() {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < thesisData.size(); i++) {
            ListItemModel listItemModel = new ListItemModel();

            if (thesisData.get(i).getMetadata().getTitle_full() == null){
                listItemModel.setTitle(thesisData.get(i).getResource().getNode_title());
            }else{
                listItemModel.setTitle(thesisData.get(i).getMetadata().getTitle_full());
            }

            if (thesisData.get(i).getResource().getImage_url().isEmpty()){
                listItemModel.setHeaderImage("https://www.teachingenglish.org.uk/sites/teacheng/files/styles/large/public/images/class_journals_iStock_000021675732XSmall.jpg?itok=eRUojT6a");
                listItemModel.setImage("https://www.teachingenglish.org.uk/sites/teacheng/files/styles/large/public/images/class_journals_iStock_000021675732XSmall.jpg?itok=eRUojT6a");
            }else{
                listItemModel.setHeaderImage(thesisData.get(i).getResource().getImage_url());
                listItemModel.setImage(thesisData.get(i).getResource().getImage_url());
            }

            if (thesisData.get(i).getMetadata().getDescription() == null){
                listItemModel.setDescription(thesisData.get(i).getResource().getNode_title());
            }else{
                listItemModel.setDescription(thesisData.get(i).getMetadata().getDescription());
            }

            listItemModel.setEntityId(thesisData.get(i).getResource().getEntity_id());

            listItem.add(listItemModel);
        }
        return listItem;
    }
}
