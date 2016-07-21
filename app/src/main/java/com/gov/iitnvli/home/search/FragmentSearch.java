package com.gov.iitnvli.home.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.SearchDataModel;
import com.gov.iitnvli.home.LandingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment {


    private View parentView;
    private LandingActivity activity;
    private ListView searchListView;
    private SearchListAdapter searchListAdapter;

    public FragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (LandingActivity) getActivity();

        if (parentView != null){
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_search, container, false);

        searchListView = (ListView) parentView.findViewById(R.id.searchListView);
        searchListAdapter = new SearchListAdapter(activity);
        setListData();
        searchListView.setAdapter(searchListAdapter);

        return parentView;
    }

    private void setListData() {
        for (int i = 0; i < 30; i++) {
            SearchDataModel searchDataModel = new SearchDataModel();
            if (i % 4 == 0) {
                searchDataModel.setHeader("Header " +i);
                searchListAdapter.addSectionHeaderItem(searchDataModel);
            }
            searchDataModel.setTitle("Row" + i);
            searchDataModel.setDescription("Description" + i);
            searchListAdapter.addItem(searchDataModel);
        }

    }

}
