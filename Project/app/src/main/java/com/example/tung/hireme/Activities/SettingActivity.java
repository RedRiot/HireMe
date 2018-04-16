package com.example.tung.hireme.Activities;

import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tung.hireme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {
    private EditText mNameField, mSummmaryField;
    private Button mBack, mConfirm;
    private ImageView mProfileImage;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mCostumerDatabase;

    private String userId, name, summary, profileImageUrl;

    private Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mNameField = (EditText) findViewById(R.id.names);
        mSummmaryField = (EditText) findViewById(R.id.summaries);
        mProfileImage = (ImageView) findViewById(R.id.profileImage);

        mConfirm = (Button) findViewById(R.id.confirm);
        mBack = (Button) findViewById(R.id.back);

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        mCostumerDatabase = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .child("Customers")
                .child(userId);
        getUserInfo();

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });

    }

    private void getUserInfo() {
        mCostumerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("name") != null) {
                        name = map.get("name").toString();
                        mNameField.setText(name);
                    }
                    if (map.get("summary") != null) {
                        summary = map.get("summary").toString();
                        mSummmaryField.setText(summary);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveUserInformation() {
        name = mNameField.getText().toString();
        summary = mSummmaryField.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("name", name);
        userInfo.put("summary", summary);
        mCostumerDatabase.updateChildren(userInfo);


    }
}
