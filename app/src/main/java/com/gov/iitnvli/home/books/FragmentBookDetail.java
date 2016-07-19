package com.gov.iitnvli.home.books;


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
public class FragmentBookDetail extends Fragment {

    private View parentView;
    private LandingActivity activity;
    private ImageView bookImage;
    private TextView bookTitle;
    private TextView bookEdition;
    private TextView bookYear;
    private TextView bookDescription;
    private TextView bookAuthor;
    private String imgUrl = "http://media.wiley.com/product_data/coverImage300/47/04704754/0470475447.jpg";
    private ListItemModel listItemModel;
    private ImageView backBtn;

    public FragmentBookDetail() {
        // Required empty public constructor
    }

    public void setBookDetailData(ListItemModel listItemModel){

        this.listItemModel = listItemModel;
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

        backBtn = (ImageView) parentView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        bookTitle = (TextView) parentView.findViewById(R.id.bookTitle);
        bookEdition = (TextView) parentView.findViewById(R.id.bookEdition);
        bookYear = (TextView) parentView.findViewById(R.id.bookYear);
        bookDescription = (TextView) parentView.findViewById(R.id.bookDescription);
        bookAuthor = (TextView) parentView.findViewById(R.id.bookAuthor);

        bookTitle.setText(listItemModel.getTitle());
        bookEdition.setText(listItemModel.getEdition());
        bookYear.setText(listItemModel.getYear());
        bookDescription.setText(listItemModel.getDescription());
        bookAuthor.setText(listItemModel.getAuthor());

        return parentView;
    }

}
