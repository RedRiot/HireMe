package com.example.tung.hireme.Activities.fragments.company;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tung.hireme.Activities.activities.CardActivity;
import com.example.tung.hireme.Activities.adapters.SavedStudentAdapter;
import com.example.tung.hireme.Activities.models.Card;
import com.example.tung.hireme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectStudentFragment extends Fragment {

    private Button startButton;
    private RecyclerView recyclerView;
    private SavedStudentAdapter adapter;
    private List<Card> students;
    private DatabaseReference userDb;
    private FirebaseAuth mAuth;
    private String currentUId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_student_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        currentUId = mAuth.getCurrentUser().getUid();
        adapter = new SavedStudentAdapter(getActivity());
        students = new ArrayList<>();
        userDb = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(currentUId)
                .child("saved");
        userDb.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String id = dataSnapshot.getKey();
                    String name = dataSnapshot.child("name").getValue().toString();
                    String summary = dataSnapshot.child("summary").getValue().toString();
                    String image = dataSnapshot.child("profileImageUrl").getValue().toString();
                    Log.d("dgfgdfg", dataSnapshot.getKey());
                    Card cards = new Card(id, name, summary, image);
                    students.add(cards);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView = view.findViewById(R.id.apilistcalendars);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.addNames(students);
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
