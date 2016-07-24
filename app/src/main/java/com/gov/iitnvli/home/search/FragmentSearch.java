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
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.SearchDataModel;
import com.gov.iitnvli.datamodel.SearchListModel;
import com.gov.iitnvli.global.AppConstants;
import com.gov.iitnvli.home.LandingActivity;
import com.gov.iitnvli.httpcommunication.httpmanager.HttpRequestManager;
import com.gov.iitnvli.httpcommunication.httpmanager.RequestType;
import com.gov.iitnvli.httpcommunication.httpmanager.ResponseHandler;
import com.gov.iitnvli.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment implements TextView.OnEditorActionListener, ResponseHandler {


    private RecyclerView searchListView;
    private View parentView;
    private LinearLayoutManager layoutManager;
    private LandingActivity activity;
    private SearchListAdapter searchListAdapter;
    private EditText searchET;
    private ImageView backBtn;
    private HttpRequestManager httpRequestManager;
    private HashMap<String, ArrayList<SearchListModel>> groupMap;

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
        httpRequestManager = new HttpRequestManager(activity, this);
        groupMap = new HashMap<>();

        if (parentView != null) {
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_search, container, false);
        backBtn = (ImageView) parentView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.hideKeyboard();
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        searchET = (EditText) parentView.findViewById(R.id.searchET);
        searchET.setOnEditorActionListener(this);
        searchET.requestFocus();
        searchListView = (RecyclerView) parentView.findViewById(R.id.searchListView);
        layoutManager = new LinearLayoutManager(activity);
        searchListView.setLayoutManager(layoutManager);
        searchListAdapter = new SearchListAdapter(activity);
        return parentView;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            activity.hideKeyboard();
            String searchStr = searchET.getText().toString().trim();
            if (!searchStr.isEmpty()) {
                groupMap.clear();
                searchListAdapter.clearData();
                String[] tabsArry = getResources().getStringArray(R.array.tabArry);
                httpRequestManager.getSearchResult(searchStr, tabsArry[AppConstants.currentTabIdx], "0", "5");
            }
            return true;
        }
        return false;
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseObject == null) {
            return;
        }

        if (responseType.equals(RequestType.GET_SEARCH_RESULT)) {

            groupDataAsPerType((SearchDataModel) responseObject);
            String[] typeKeys = activity.getResources().getStringArray(R.array.tabArry);

            //This is for the current tab data//
            if (groupMap.containsKey(typeKeys[AppConstants.currentTabIdx])){
                setDataToList(groupMap.get(typeKeys[AppConstants.currentTabIdx]));
            }

            //This is for all other remaining tab data//
            setRemainingTabData(typeKeys[AppConstants.currentTabIdx]);

            searchListView.setAdapter(searchListAdapter);
        }
    }

    private void setRemainingTabData(String currentKey) {
        Iterator iterator = groupMap.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry pair = (Map.Entry) iterator.next();
            ArrayList<SearchListModel> listData = (ArrayList<SearchListModel>) pair.getValue();

            if (!currentKey.equalsIgnoreCase(String.valueOf(pair.getKey()))){
                setDataToList(listData);
            }
        }
    }

    private void groupDataAsPerType(SearchDataModel searchDataModel) {
        for (int i = 0; i < searchDataModel.getResult().size(); i++) {
            SearchDataModel.ResultBean.MetadataBean metadataBean = searchDataModel.getResult().get(i).getMetadata();
            SearchDataModel.ResultBean.ResourceBean resourceBean = searchDataModel.getResult().get(i).getResource();

            String key = "Others";
            if (resourceBean.getType() != null && !resourceBean.getType().isEmpty()) {
                key = resourceBean.getType();
            }
            if (!groupMap.containsKey(key)) {
                groupMap.put(key, new ArrayList<SearchListModel>());
            }
            groupMap.get(key).add(getSearchList(key, searchDataModel.getResult().get(i)));
        }
    }

    private SearchListModel getSearchList(String key, SearchDataModel.ResultBean resultBean) {
        SearchListModel searchListModel = new SearchListModel();

        searchListModel.setHeader(key);

        if (resultBean.getMetadata().getTitle_full() == null) {
            searchListModel.setTitle(resultBean.getResource().getNode_title());
        } else {
            searchListModel.setTitle(resultBean.getMetadata().getTitle_full());
        }

        if (resultBean.getResource().getImage_url().isEmpty()) {
            searchListModel.setImageRes(Utility.getImageResBasedOnType(key, activity));
        } else {
            searchListModel.setImageUrl(resultBean.getResource().getImage_url());
        }

        if (resultBean.getMetadata().getDescription() == null) {
            searchListModel.setDescription(resultBean.getResource().getNode_title());
        } else {
            searchListModel.setDescription(resultBean.getMetadata().getDescription());
        }

        return searchListModel;
    }

    public void setDataToList(ArrayList<SearchListModel> listData) {
        if (!listData.isEmpty()){
            searchListAdapter.addSectionHeaderItem(listData.get(0));
            for (int i = 0; i < listData.size(); i++) {
                searchListAdapter.addItem(listData.get(i));
            }
        }
    }
}
