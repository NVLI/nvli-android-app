package com.gov.iitnvli.home.thesis;


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
import com.gov.iitnvli.datamodel.ListItemModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThesisDetail extends Fragment {

    private View parentView;
    private LandingActivity activity;
    private ImageView thesisImage;
    private TextView thesisTitle;
    private TextView thesisPublisherName;
    private TextView thesisYear;
    private TextView thesisDescription;
    private TextView thesisAuthor;
    private String imgUrl = "https://www.teachingenglish.org.uk/sites/teacheng/files/styles/large/public/images/class_journals_iStock_000021675732XSmall.jpg?itok=eRUojT6a";
    private ImageView backBtn;
    private DetailsDataModel detailsDataModel;

    public FragmentThesisDetail() {
        // Required empty public constructor
    }

    public void setThesisDetailData(DetailsDataModel detailsDataModel){
        this.detailsDataModel = detailsDataModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (LandingActivity) getActivity();

        if (parentView != null){
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_thesis_detail, container, false);

        backBtn = (ImageView) parentView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        thesisImage = (ImageView) parentView.findViewById(R.id.theisImage);
        thesisTitle = (TextView) parentView.findViewById(R.id.thesisTitle);
        thesisPublisherName = (TextView) parentView.findViewById(R.id.thesisPublisherName);
        thesisYear = (TextView) parentView.findViewById(R.id.theisPublishYear);
        thesisDescription = (TextView) parentView.findViewById(R.id.thesisDescription);
        thesisAuthor = (TextView) parentView.findViewById(R.id.thesisAuthor);

        DetailsDataModel.ResultBean.MetadataBean metadataBean = detailsDataModel.getResult().get(0).getMetadata();
        DetailsDataModel.ResultBean.ResourceBean resourceBean = detailsDataModel.getResult().get(0).getResource();

        if (metadataBean.getTitle_full() == null) {
            thesisTitle.setText(resourceBean.getNode_title());
        } else {
            thesisTitle.setText(metadataBean.getTitle_full());
        }

        if (resourceBean.getImage_url().isEmpty()) {
            Picasso.with(activity).load(imgUrl).into(thesisImage);
        } else {
            Picasso.with(activity).load(resourceBean.getImage_url()).into(thesisImage);
        }

        if (!metadataBean.getPublisher().isEmpty()) {
            thesisPublisherName.setText("Published By - " + metadataBean.getPublisher().get(0));
        } else {
            thesisPublisherName.setVisibility(View.GONE);
        }

        if (!metadataBean.getPublishDate().isEmpty()) {
            thesisYear.setText(metadataBean.getPublishDate().get(0));
        } else {
            thesisYear.setVisibility(View.GONE);
        }

        if (metadataBean.getDescription() == null) {
            thesisDescription.setText(resourceBean.getNode_title());
        } else {
            thesisDescription.setText(metadataBean.getDescription());
        }

        if (!metadataBean.getAuthor().isEmpty()) {
            thesisAuthor.setText(metadataBean.getAuthor().get(0));
        } else {
            thesisAuthor.setVisibility(View.GONE);
        }

        return parentView;
    }

}
