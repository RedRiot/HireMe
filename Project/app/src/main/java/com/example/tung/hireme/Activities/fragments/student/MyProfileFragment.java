package com.example.tung.hireme.Activities.fragments.student;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tung.hireme.Activities.activities.CardActivity;
import com.example.tung.hireme.Activities.adapters.CardAdapter;
import com.example.tung.hireme.Activities.models.Card;
import com.example.tung.hireme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyProfileFragment extends Fragment {
    private Card card;
    private ArrayList<Card> rowItem;
    private CardAdapter adapter;
    protected FirebaseAuth auth;
    private DatabaseReference userDB;
    private String userId,nameProfile,summaryProfile,image;
    private TextView name, summary;
    private ImageView imageView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_myprofile, container, false);

        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        userDB = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(userId);

        final TextView name = (TextView) view.findViewById(R.id.namest);
        final TextView summary = (TextView) view.findViewById(R.id.summaryst);
        final ImageView imageView = (ImageView) view.findViewById(R.id.profileImageViewSt);

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("name").getValue().toString() != null && dataSnapshot.child("summary").getValue().toString() != null && dataSnapshot.child("profileImageUrl").getValue().toString() != null) {
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    summary.setText(dataSnapshot.child("summary").getValue().toString());
                    Glide.with(getContext()).load(dataSnapshot.child("profileImageUrl").getValue().toString()).into(imageView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
