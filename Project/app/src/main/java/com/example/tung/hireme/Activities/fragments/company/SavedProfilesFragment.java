package com.example.tung.hireme.Activities.fragments.company;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tung.hireme.R;

public class SavedProfilesFragment extends Fragment {
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //this should changed with RecycleView
        View view = inflater.inflate(R.layout.saved_profile_fragment, container, false);

        return view;
    }
}
