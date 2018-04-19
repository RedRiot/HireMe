package com.example.tung.hireme.Activities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tung.hireme.Activities.fragments.SettingsFragment;
import com.example.tung.hireme.Activities.fragments.company.SavedProfilesFragment;
import com.example.tung.hireme.Activities.fragments.company.SelectStudentFragment;
import com.example.tung.hireme.Activities.fragments.student.EditProfileFragment;
import com.example.tung.hireme.Activities.fragments.student.MyProfileFragment;

/**
 * Created by tung on 4/18/18.
 */
public class StudentAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public StudentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MyProfileFragment tab1 = new MyProfileFragment();
                return tab1;
            case 1:
                EditProfileFragment tab2 = new EditProfileFragment();
                return tab2;
            case 2:
                SettingsFragment tab3 = new SettingsFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
