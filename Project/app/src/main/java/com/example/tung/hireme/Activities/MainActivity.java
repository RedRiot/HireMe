package com.example.tung.hireme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tung.hireme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
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

        userDb = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        currentUId = mAuth.getCurrentUser().getUid();

        checkUserType();
        rowItems = new ArrayList<Card>();

        arrayAdapter = new CardAdapter(this, R.layout.card_view,rowItems);

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
                userDb.child(notUserType)
                        .child(userId)
                        .child("connections")
                        .child("no")
                        .child(currentUId)
                        .setValue(true);
                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Card obj = (Card) dataObject;
                String userId = obj.getUserId();
                userDb.child(notUserType)
                        .child(userId)
                        .child("connections")
                        .child("yes")
                        .child(currentUId)
                        .setValue(true);
                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String userType;
    private String notUserType;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public void checkUserType() {
        DatabaseReference companyDb = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child("Company");
        companyDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.getKey().equals(user.getUid())) {
                    userType = "Company";
                    notUserType = "Student";
                    getOpppositeUsers();
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

    public void getOpppositeUsers() {
        DatabaseReference studentDb = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(notUserType);
        studentDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.exists()) {
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

    public void logoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void gotoSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();

    }
}
