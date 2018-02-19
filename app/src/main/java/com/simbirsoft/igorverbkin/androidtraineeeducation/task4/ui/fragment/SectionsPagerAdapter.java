package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.MainActivity.TAG;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public <T extends Fragment> void addFragment(Class<T> fragmentClass) {
        try {
            fragments.add(fragmentClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            Log.d(TAG, e.getMessage());
        }
    }
}
