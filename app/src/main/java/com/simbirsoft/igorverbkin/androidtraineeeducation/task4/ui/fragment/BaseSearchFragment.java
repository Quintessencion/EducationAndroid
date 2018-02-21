package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

public abstract class BaseSearchFragment extends MvpAppCompatFragment implements SearchView.OnQueryTextListener, SearchNkoView {

    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_bar_search_menu);
        searchView = (SearchView) menuItem.getActionView();

        searchView.setFocusable(true);
        searchView.setBackgroundColor(getActivity().getResources().getColor(android.R.color.white));
        searchView.setOnQueryTextListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            searchView.setElevation(16);
        }
        setHint(searchView);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    protected abstract void setHint(SearchView view);

    @Override
    public void setQueryToSearchView(String query) {
        searchView.setQuery(query, true);
    }

    @Override
    public void onStop() {
        setMenuVisibility(false);
        super.onStop();
    }
}
