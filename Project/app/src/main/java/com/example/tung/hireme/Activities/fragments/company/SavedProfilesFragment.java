package com.example.tung.hireme.Activities.fragments.company;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tung.hireme.Activities.adapters.SavedStudentAdapter;
import com.example.tung.hireme.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SavedProfilesFragment extends Fragment {
    private RecyclerView recyclerView;
    private SavedStudentAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //this should changed with RecycleView
        View view = inflater.inflate(R.layout.saved_profile_fragment, container, false);
        adapter = new SavedStudentAdapter(getActivity());
        recyclerView = view.findViewById(R.id.apilistcalendars);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        List<String> names = new ArrayList<>(Arrays.asList("adasd", "asdasdasd", "fsdfr"));
        adapter.addNames(names);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
