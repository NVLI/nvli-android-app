package com.gov.iitnvli.home.general;


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
public class FragmentGeneralDetail extends Fragment {

    private View parentView;
    private LandingActivity activity;
    private ImageView image;
    private TextView title;
    private TextView publisher;
    private TextView year;
    private TextView description;
    private TextView author;
    private String imgUrl = "http://inanutshell.ca/wp-content/uploads/2016/01/Books-Banner.jpg";
    private ImageView backBtn;
    private DetailsDataModel detailsDataModel;

    public FragmentGeneralDetail() {
        // Required empty public constructor
    }

    public void setDetailData(DetailsDataModel detailsDataModel) {
        this.detailsDataModel = detailsDataModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (LandingActivity) getActivity();

        if (parentView != null) {
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_general_detail, container, false);

        backBtn = (ImageView) parentView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        title = (TextView) parentView.findViewById(R.id.bookTitle);
        image = (ImageView) parentView.findViewById(R.id.bookImage);
        publisher = (TextView) parentView.findViewById(R.id.publisher);
        year = (TextView) parentView.findViewById(R.id.bookYear);
        description = (TextView) parentView.findViewById(R.id.bookDescription);
        author = (TextView) parentView.findViewById(R.id.bookAuthor);

        DetailsDataModel.ResultBean.MetadataBean metadataBean = detailsDataModel.getResult().get(0).getMetadata();
        DetailsDataModel.ResultBean.ResourceBean resourceBean = detailsDataModel.getResult().get(0).getResource();

        if (metadataBean.getTitle_full() == null) {
            title.setText(resourceBean.getNode_title());
        } else {
            title.setText(metadataBean.getTitle_full());
        }

        if (resourceBean.getImage_url() != null){
            if (resourceBean.getImage_url().isEmpty()) {
                Picasso.with(activity).load(imgUrl).into(image);
            } else {
                Picasso.with(activity).load(resourceBean.getImage_url()).into(image);
            }
        }

        if ((metadataBean.getPublisher() != null) && (!metadataBean.getPublisher().isEmpty())) {
            publisher.setText("Published By - " + metadataBean.getPublisher().get(0));
        } else {
            publisher.setVisibility(View.GONE);
        }

        if (metadataBean.getPublishDate() != null){
            if (!metadataBean.getPublishDate().isEmpty()) {
                year.setText(metadataBean.getPublishDate().get(0));
            } else {
                year.setVisibility(View.GONE);
            }
        }

        if (metadataBean.getDescription() == null) {
            description.setText(resourceBean.getNode_title());
        } else {
            description.setText(metadataBean.getDescription());
        }

        if (metadataBean.getAuthor() != null){
            if (!metadataBean.getAuthor().isEmpty()) {
                author.setText(metadataBean.getAuthor().get(0));
            } else {
                author.setVisibility(View.GONE);
            }
        }

        return parentView;
    }

}
