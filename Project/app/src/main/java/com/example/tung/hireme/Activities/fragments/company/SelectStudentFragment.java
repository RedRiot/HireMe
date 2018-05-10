package com.example.tung.hireme.Activities.fragments.company;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tung.hireme.Activities.activities.CardActivity;
import com.example.tung.hireme.Activities.adapters.SavedStudentAdapter;
import com.example.tung.hireme.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectStudentFragment extends Fragment {

    private Button startButton;
    private RecyclerView recyclerView;
    private SavedStudentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_student_fragment, container, false);

        adapter = new SavedStudentAdapter(getActivity());
        recyclerView = view.findViewById(R.id.apilistcalendars);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Set<String> studentNames =  prefs.getStringSet("list", null);
        if (studentNames != null) {
            List<String> names = new ArrayList<>(studentNames);
            adapter.addNames(names);
        }
        recyclerView.setAdapter(adapter);

        startButton = (Button) view.findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CardActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
