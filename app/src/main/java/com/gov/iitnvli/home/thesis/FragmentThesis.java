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
import com.gov.iitnvli.global.ActivityConstant;
import com.gov.iitnvli.home.LandingActivity;
import com.gov.iitnvli.home.ListRecyclerViewAdapter;
import com.gov.iitnvli.httpcommunication.datamodel.ListItemModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThesis extends Fragment {

    private RecyclerView thesisList;
    private ListRecyclerViewAdapter adapter;
    private View parentView;
    private LinearLayoutManager layoutManager;
    private LandingActivity activity;

    public FragmentThesis() {
        // Required empty public constructor
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
        adapter = new ListRecyclerViewAdapter(getTestData(), activity, ActivityConstant.THESISK_DETAIL_FRAGMENT);
        thesisList.setAdapter(adapter);

        return parentView;
    }

    public ArrayList<ListItemModel> getTestData() {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ListItemModel listItemModel = new ListItemModel();
            listItemModel.setTitle("Guide to the successful thesis and dissertation : a handbook for students and faculty");
            listItemModel.setHeaderImage("http://www.uiowa.edu/icru/files/icru/files/styles/large/public/honors%20thesis.jpg?itok=qZ8a0Cr3");
            listItemModel.setImage("http://coverart.oclc.org/ImageWebSvc/oclc/+-+08790499_140.jpg?SearchOrder=+-+OT,OS,TN,GO,FA");
            listItemModel.setDescription("Updated to reflect recent trends in thesis/dissertation preparation and research, this book examines confidentiality and privacy in Internet communications and considers the accuracy and reliability of Internet-reported research.");
            listItemModel.setSubcategory("Academic");
            listItemModel.setEdition("New York : M. Dekker");
            listItemModel.setYear("1998");
            listItemModel.setAuthor("James E Mauch, Jack W Birch");
            listItem.add(listItemModel);
        }
        return listItem;
    }
}
