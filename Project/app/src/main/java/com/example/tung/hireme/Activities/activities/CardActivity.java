package com.example.tung.hireme.Activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tung.hireme.Activities.adapters.CardAdapter;
import com.example.tung.hireme.Activities.fragments.company.SelectStudentFragment;
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
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CardActivity extends AppCompatActivity {
    private Card cards[];

    private CardAdapter arrayAdapter;
    private int i;

    private FirebaseAuth mAuth;
    ListView listView;
    List<Card> rowItems;

    private DatabaseReference userDb;
    private String currentUId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDb = FirebaseDatabase.getInstance().getReference().child("Users").child("type");

        mAuth = FirebaseAuth.getInstance();
        currentUId = mAuth.getCurrentUser().getUid();

        checkUserType();
        rowItems = new ArrayList<Card>();

        arrayAdapter = new CardAdapter(CardActivity.this, R.layout.card_view,rowItems);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Card obj = (Card) dataObject;
                String userId = obj.getUserId();
                userDb.child(userId)
                      .child("connections")
                      .child("no")
                      .child(currentUId)
                      .setValue(true);
                Toast.makeText(CardActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Card obj = (Card) dataObject;
                String userId = obj.getUserId();
                userDb.child(userId)
                      .child("connections")
                      .child("yes")
                      .child(currentUId)
                      .setValue(true);
                Toast.makeText(CardActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(CardActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String userType;
    private String notUserType;

    public void checkUserType() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(user.getUid());
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getKey().equals(user.getUid())) {
                    if (dataSnapshot.exists()) {
                        if (dataSnapshot.child("type") != null) {
                            userType = dataSnapshot.child("type").getValue().toString();
                            switch (userType) {
                                case "Company":
                                    notUserType = "Student";
                                    break;
                                case "Student":
                                    notUserType = "Company";
                                    break;
                            }
                            getOppositeUsers();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getOppositeUsers() {
        userDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.exists() && dataSnapshot.child("type").getValue().equals(notUserType)) {
                    Card item = new Card(dataSnapshot.getKey(),
                            dataSnapshot.child("name").getValue().toString(),
                            dataSnapshot.child("summary").getValue().toString(),
                            dataSnapshot.child("profileImageUrl").getValue().toString());
                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void goBackToFragment(View view) {
        Intent intent = new Intent(CardActivity.this, CompanyActivity.class);
        startActivity(intent);
    }
}
