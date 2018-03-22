package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

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
            Logger.d(e.getMessage());
        }
    }
}
