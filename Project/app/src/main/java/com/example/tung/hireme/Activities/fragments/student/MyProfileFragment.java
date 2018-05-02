package com.example.tung.hireme.Activities.fragments.student;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tung.hireme.Activities.activities.CardActivity;
import com.example.tung.hireme.Activities.adapters.CardAdapter;
import com.example.tung.hireme.Activities.models.Card;
import com.example.tung.hireme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

public class MyProfileFragment extends Fragment{
    private Card card;
    private List<Card> rowItem;
    private CardAdapter adapter;
    protected FirebaseAuth auth;
    private DatabaseReference userDB;
    private String userId;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_myprofile, container, false);

        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        userDB = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(userId);

        getUserInfo();

        adapter = new CardAdapter(getContext(), R.layout.card_view,rowItem);

        return view;
    }

    private void getUserInfo() {
        userDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Card card = new Card(dataSnapshot.getKey(),
                            dataSnapshot.child("name").getValue().toString(),
                            dataSnapshot.child("summary").getValue().toString(),
                            dataSnapshot.child("profileImageUrl").getValue().toString());
                    rowItem.add(card);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
