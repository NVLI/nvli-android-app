package com.gov.iitnvli.home;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBookDetail extends Fragment {

    private View parentView;
    private LandingActivity activity;
    private ImageView bookImage;
    private String imgUrl = "http://media.wiley.com/product_data/coverImage300/47/04704754/0470475447.jpg";

    public FragmentBookDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (LandingActivity) getActivity();

        if (parentView != null){
            return parentView;
        }

        parentView = inflater.inflate(R.layout.fragment_book_detail, container, false);

        bookImage = (ImageView) parentView.findViewById(R.id.bookImage);
        Picasso.with(activity).load(imgUrl).into(bookImage);
        return parentView;
    }

}
