package com.example.tung.hireme.Activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tung.hireme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class EmptyActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference userDB;
    private String userType, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);

        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        userDB = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child(userId);
        getUserInfo();
    }

    private void getUserInfo() {
        userDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("type") != null) {
                        userType = map.get("type").toString();
                        checkUserType(userType);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void checkUserType(String userType) {
        if (userType.equals("Company")) {
            Intent i = new Intent(EmptyActivity.this, CompanyActivity.class);
            startActivity(i);
            finish();
        } else if (userType.equals("Student")) {
            Intent i = new Intent(EmptyActivity.this, StudentActivity.class);
            startActivity(i);
            finish();
        }
    }
}
