package com.example.tung.hireme.Activities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tung.hireme.Activities.fragments.SettingsFragment;
import com.example.tung.hireme.Activities.fragments.company.SavedProfilesFragment;
import com.example.tung.hireme.Activities.fragments.company.SelectStudentFragment;

/**
 * Created by tung on 4/18/18.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SelectStudentFragment tab1 = new SelectStudentFragment();
                return tab1;
            case 1:
                SavedProfilesFragment tab2 = new SavedProfilesFragment();
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
