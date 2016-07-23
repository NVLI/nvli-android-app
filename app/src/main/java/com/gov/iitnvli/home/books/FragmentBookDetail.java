package com.gov.iitnvli.home.books;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.DetailsDataModel;
import com.gov.iitnvli.home.LandingActivity;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBookDetail extends Fragment {

    private View parentView;
    private LandingActivity activity;
    private ImageView bookImage;
    private TextView bookTitle;
    private TextView publisher;
    private TextView bookYear;
    private TextView bookDescription;
    private TextView bookAuthor;
    private String imgUrl = "http://inanutshell.ca/wp-content/uploads/2016/01/Books-Banner.jpg";
    private ImageView backBtn;
    private DetailsDataModel detailsDataModel;

    public FragmentBookDetail() {
        // Required empty public constructor
    }

    public void setBookDetailData(DetailsDataModel detailsDataModel) {
        this.detailsDataModel = detailsDataModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (LandingActivity) getActivity();

        if (parentView != null) {
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_book_detail, container, false);

        backBtn = (ImageView) parentView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        bookTitle = (TextView) parentView.findViewById(R.id.bookTitle);
        bookImage = (ImageView) parentView.findViewById(R.id.bookImage);
        publisher = (TextView) parentView.findViewById(R.id.publisher);
        bookYear = (TextView) parentView.findViewById(R.id.bookYear);
        bookDescription = (TextView) parentView.findViewById(R.id.bookDescription);
        bookAuthor = (TextView) parentView.findViewById(R.id.bookAuthor);

        DetailsDataModel.ResultBean.MetadataBean metadataBean = detailsDataModel.getResult().get(0).getMetadata();
        DetailsDataModel.ResultBean.ResourceBean resourceBean = detailsDataModel.getResult().get(0).getResource();

        if (metadataBean.getTitle_full() == null) {
            bookTitle.setText(resourceBean.getNode_title());
        } else {
            bookTitle.setText(metadataBean.getTitle_full());
        }

        if (resourceBean.getImage_url().isEmpty()) {
            Picasso.with(activity).load(imgUrl).into(bookImage);
        } else {
            Picasso.with(activity).load(resourceBean.getImage_url()).into(bookImage);
        }

        if (!metadataBean.getPublisher().isEmpty()) {
            publisher.setText("Published By - " + metadataBean.getPublisher().get(0));
        } else {
            publisher.setVisibility(View.GONE);
        }

        if (!metadataBean.getPublishDate().isEmpty()) {
            bookYear.setText(metadataBean.getPublishDate().get(0));
        } else {
            bookYear.setVisibility(View.GONE);
        }

        if (metadataBean.getDescription() == null) {
            bookDescription.setText(resourceBean.getNode_title());
        } else {
            bookDescription.setText(metadataBean.getDescription());
        }

        if (!metadataBean.getAuthor().isEmpty()) {
            bookAuthor.setText(metadataBean.getAuthor().get(0));
        } else {
            bookAuthor.setVisibility(View.GONE);
        }

        return parentView;
    }

}
