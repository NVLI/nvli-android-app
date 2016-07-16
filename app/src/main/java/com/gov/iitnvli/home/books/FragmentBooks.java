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
import com.gov.iitnvli.global.ActivityConstant;
import com.gov.iitnvli.home.LandingActivity;
import com.gov.iitnvli.home.ListRecyclerViewAdapter;
import com.gov.iitnvli.httpcommunication.datamodel.ListItemModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBooks extends Fragment {

    private RecyclerView booksList;
    private ListRecyclerViewAdapter adapter;
    private View parentView;
    private LinearLayoutManager layoutManager;
    private LandingActivity activity;

    public FragmentBooks() {
        // Required empty public constructor
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
        adapter = new ListRecyclerViewAdapter(getTestData(), activity, ActivityConstant.BOOK_DETAIL_FRAGMENT);
        booksList.setAdapter(adapter);

        return parentView;
    }

    public ArrayList<ListItemModel> getTestData() {
        ArrayList<ListItemModel> listItem = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ListItemModel listItemModel = new ListItemModel();
            listItemModel.setTitle("Physics / John D. Cutnell, Kenneth W. Johnson");
            listItemModel.setHeaderImage("http://i.imgur.com/hke6CkU.jpg");
            listItemModel.setImage("http://media.wiley.com/product_data/coverImage300/47/04704754/0470475447.jpg");
            listItemModel.setDescription("Introduction and mathematical concepts -- Kinematics in one dimension -- Kinematics in two dimensions -- Forces and Newton's Laws of Motion -- Dynamics of uniform circular motion -- Work and energy -- Impulse and momentum -- Rotational kinematics -- Rotational dynamics -- Simple harmonic motion and elasticity -- Fluids -- Temperature and heat -- Transfer of heat -- Ideal gas law and kinetic theory -- Thermodynamics -- Waves and sound -- Principle of linear superposition and interference phenomena -- Electric forces and electric fields -- Electric pootential energy and the electric potential -- Electric circuits -- Magnetic forces and magnetic fields -- Electromagentic induction -- Alternating current circuits -- Electromagentic waves -- Reflection of light: mirrors -- Refraction of light: lenses and optical instruments -- Interference andt he wave nature of light -- Special relativity -- Particles and waves -- Nature of the atom -- Nuclear physics and radioactivity -- Ionizing radiation, nuclear energy, and elementary particles.");
            listItemModel.setSubcategory("Physics");
            listItemModel.setEdition("8th Edition");
            listItemModel.setYear("2012");
            listItemModel.setAuthor("John D. Cutnell, Kenneth W. Johnson");
            listItem.add(listItemModel);
        }
        return listItem;
    }
}
