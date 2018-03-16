package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.EventCategoryActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.EventCategoryActivity.BUNDLE_CATEGORY;

public class HelpFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.children, R.id.adults, R.id.elderly, R.id.pets, R.id.events})
    public void onCategoryClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.children:
                showEvents(Category.CHILDREN);
                break;
            case R.id.adults:
                showEvents(Category.ADULTS);
                break;
            case R.id.elderly:
                showEvents(Category.ELDERLY);
                break;
            case R.id.pets:
                showEvents(Category.PETS);
                break;
            case R.id.events:
                showEvents(Category.EVENTS);
                break;
        }
    }

    private void showEvents(Category category) {
        Intent intent = new Intent(getActivity(), EventCategoryActivity.class);
        intent.putExtra(BUNDLE_CATEGORY, category);
        startActivity(intent);
    }
}
