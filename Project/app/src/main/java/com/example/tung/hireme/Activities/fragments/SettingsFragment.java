package com.example.tung.hireme.Activities.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.tung.hireme.Activities.activities.CardActivity;
import com.example.tung.hireme.Activities.activities.LoginActivity;
import com.example.tung.hireme.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);


        return view;
    }
    public void logoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

    }
}
