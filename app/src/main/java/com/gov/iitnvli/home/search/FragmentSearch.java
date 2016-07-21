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
    private ImageView backBtn;

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

        setTestData(getString(R.string.books), R.drawable.book_square);
        setTestData(getString(R.string.thesis), R.drawable.thesis_square);
        setTestData(getString(R.string.museum), R.drawable.musem_square);
        setTestData(getString(R.string.archieves), R.drawable.archieve_square);
        setTestData(getString(R.string.audiovideo), R.drawable.video_square);
        setTestData(getString(R.string.manuscripts), R.drawable.manuscrip_square);
        setTestData(getString(R.string.newspaper), R.drawable.newspaper_square);
        setTestData(getString(R.string.maps), R.drawable.map_sqare);

        searchListView.setAdapter(searchListAdapter);

        return parentView;
    }

    private void setTestData(String headerTitle, int imageRes) {
        SearchDataModel searchDataModel = new SearchDataModel();
        searchDataModel.setHeader(headerTitle);
        searchDataModel.setImageRes(imageRes);
        searchListAdapter.addSectionHeaderItem(searchDataModel);
        for (int i = 0; i < 3; i++) {
            searchDataModel.setTitle("Physics / John D. Cutnell, Kenneth W. Johnson");
            searchDataModel.setDescription("Introduction and mathematical concepts -- Kinematics in one dimension -- Kinematics in two dimensions -- Forces and Newton's Laws of Motion -- Dynamics of uniform circular motion -- Work and energy -- Impulse and momentum -- Rotational kinematics -- Rotational dynamics -- Simple harmonic motion and elasticity -- Fluids -- Temperature and heat -- Transfer of heat -- Ideal gas law and kinetic theory -- Thermodynamics -- Waves and sound -- Principle of linear superposition and interference phenomena -- Electric forces and electric fields -- Electric pootential energy and the electric potential -- Electric circuits -- Magnetic forces and magnetic fields -- Electromagentic induction -- Alternating current circuits -- Electromagentic waves -- Reflection of light: mirrors -- Refraction of light: lenses and optical instruments -- Interference andt he wave nature of light -- Special relativity -- Particles and waves -- Nature of the atom -- Nuclear physics and radioactivity -- Ionizing radiation, nuclear energy, and elementary particles.");
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
