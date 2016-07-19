package com.gov.iitnvli.home.thesis;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
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
    private String imgUrl = "http://coverart.oclc.org/ImageWebSvc/oclc/+-+08790499_140.jpg?SearchOrder=+-+OT,OS,TN,GO,FA";
    private ListItemModel listItemModel;
    private ImageView backBtn;

    public FragmentThesisDetail() {
        // Required empty public constructor
    }

    public void setThesisDetailData(ListItemModel listItemModel){

        this.listItemModel = listItemModel;
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
        Picasso.with(activity).load(imgUrl).into(thesisImage);

        thesisTitle = (TextView) parentView.findViewById(R.id.thesisTitle);
        thesisPublisherName = (TextView) parentView.findViewById(R.id.thesisPublisherName);
        thesisYear = (TextView) parentView.findViewById(R.id.theisPublishYear);
        thesisDescription = (TextView) parentView.findViewById(R.id.thesisDescription);
        thesisAuthor = (TextView) parentView.findViewById(R.id.thesisAuthor);

        thesisTitle.setText(listItemModel.getTitle());
        thesisPublisherName.setText(listItemModel.getEdition());
        thesisYear.setText(listItemModel.getYear());
        thesisDescription.setText(listItemModel.getDescription());
        thesisAuthor.setText(listItemModel.getAuthor());

        return parentView;
    }

}
