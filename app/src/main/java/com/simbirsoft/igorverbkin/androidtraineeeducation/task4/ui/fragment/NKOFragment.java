package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.NkoEvent;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NKOFragment extends BaseSearchFragment implements SearchNkoView {

    @InjectPresenter NkoPresenter presenter;

    @BindView(R.id.layout_keywords) View layoutKeywords;
    @BindView(R.id.count_results) TextView countResult;
    @BindView(R.id.keywords) TextView keywords;

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

        ButterKnife.bind(this, view);

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
        keywords.setText(query);
        presenter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        keywords.setText(newText);
        presenter.filter(newText);
        return false;
    }

    @Override
    public void loadData(List<NkoEvent> nkos) {
        countResult.setText(getResources().getQuantityString(R.plurals.organization_plurals, nkos.size(), nkos.size()));
        adapter.updateList(nkos);
        recyclerView.scrollToPosition(0);
    }
}
