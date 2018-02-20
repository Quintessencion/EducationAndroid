package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.NkoEvent;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import java.util.List;

public class NKOFragment extends BaseSearchFragment implements SearchNkoView {

    @InjectPresenter NkoPresenter presenter;

    private RecyclerView recyclerView;
    private NkoAdapter adapter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (presenter != null) {
            presenter.refreshData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nko, container, false);

        recyclerView = view.findViewById(R.id.recycler_nko);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new NkoAdapter();
        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    protected void setHint(SearchView view) {
        view.setQueryHint(getString(R.string.search_hint));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.filter(newText);
        return false;
    }

    @Override
    public void loadData(List<NkoEvent> nkos) {
        adapter.updateList(nkos);
        recyclerView.scrollToPosition(0);
    }
}
