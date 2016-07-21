package com.gov.iitnvli.home.search;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.SearchDataModel;
import com.gov.iitnvli.home.LandingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment implements TextView.OnEditorActionListener {


    private RecyclerView searchListView;
    private View parentView;
    private LinearLayoutManager layoutManager;
    private LandingActivity activity;
    private SearchListAdapter searchListAdapter;
    private EditText searchET;

    public FragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(searchET, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (LandingActivity) getActivity();

        if (parentView != null) {
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_search, container, false);

        searchET = (EditText) parentView.findViewById(R.id.searchET);
        searchET.setOnEditorActionListener(this);
        searchET.requestFocus();
        searchListView = (RecyclerView) parentView.findViewById(R.id.searchListView);
        layoutManager = new LinearLayoutManager(activity);
        searchListView.setLayoutManager(layoutManager);
        searchListAdapter = new SearchListAdapter(activity);
        setListData();
        searchListView.setAdapter(searchListAdapter);

        return parentView;
    }

    private void setListData() {
        for (int i = 0; i < 30; i++) {
            SearchDataModel searchDataModel = new SearchDataModel();
            if (i % 4 == 0) {
                searchDataModel.setHeader("Header " + i);
                searchListAdapter.addSectionHeaderItem(searchDataModel);
            }
            searchDataModel.setTitle("Row" + i);
            searchDataModel.setDescription("Description" + i);
            searchListAdapter.addItem(searchDataModel);
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            activity.hideKeyboard();
            return true;
        }
        return false;
    }

}
