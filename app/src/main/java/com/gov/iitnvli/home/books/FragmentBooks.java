package com.gov.iitnvli.home.books;


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
public class FragmentBooks extends Fragment {

    private RecyclerView booksList;
    private ListRecyclerViewAdapter adapter;
    private View parentView;
    private LinearLayoutManager layoutManager;
    private LandingActivity activity;
    private List<DashboardDataModel.ResultBean.BooksBean> booksData;

    public FragmentBooks() {
        // Required empty public constructor
    }

    public void setBooksData(List<DashboardDataModel.ResultBean.BooksBean> booksData) {
        this.booksData = booksData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (LandingActivity) getActivity();

        if (parentView != null) {
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_books, container, false);

        booksList = (RecyclerView) parentView.findViewById(R.id.booksList);
        layoutManager = new LinearLayoutManager(activity);
        booksList.setLayoutManager(layoutManager);
        booksList.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        adapter = new ListRecyclerViewAdapter(getBooksData(), activity, ActivityConstant.GENERAL_DETAIL_FRAGMENT);
        booksList.setAdapter(adapter);

        return parentView;
    }

    private ArrayList<ListItemModel> getBooksData() {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < booksData.size(); i++) {
            ListItemModel listItemModel = new ListItemModel();

            if (booksData.get(i).getMetadata().getTitle_full() == null) {
                listItemModel.setTitle(booksData.get(i).getResource().getNode_title());
            } else {
                listItemModel.setTitle(booksData.get(i).getMetadata().getTitle_full());
            }

            if (booksData.get(i).getResource().getImage_url().isEmpty()) {
                listItemModel.setHeaderImage("http://inanutshell.ca/wp-content/uploads/2016/01/Books-Banner.jpg");
                listItemModel.setImage("http://inanutshell.ca/wp-content/uploads/2016/01/Books-Banner.jpg");
            } else {
                listItemModel.setHeaderImage(booksData.get(i).getResource().getImage_url());
                listItemModel.setImage(booksData.get(i).getResource().getImage_url());
            }

            if (booksData.get(i).getMetadata().getDescription() == null) {
                listItemModel.setDescription(booksData.get(i).getResource().getNode_title());
            } else {
                listItemModel.setDescription(booksData.get(i).getMetadata().getDescription());
            }

            listItemModel.setEntityId(booksData.get(i).getResource().getEntity_id());

            listItem.add(listItemModel);
        }
        return listItem;
    }
}
