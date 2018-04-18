package com.example.tung.hireme.Activities.fragments.company;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tung.hireme.Activities.activities.CardActivity;
import com.example.tung.hireme.R;

public class SelectStudentFragment extends Fragment {

    private Button startButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_student_fragment, container, false);
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
