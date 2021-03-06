package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.HistoryPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.HistoryView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity.EVENT_ID;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView,
        HistoryAdapter.OnItemClickListener {

    @InjectPresenter HistoryPresenter presenter;
    @Inject Repository repository;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.empty_history_screen) RelativeLayout emptyHistoryScreenLayout;
    @BindView(R.id.progress_bar) ProgressBar loading;

    private HistoryAdapter adapter;

    @ProvidePresenter
    HistoryPresenter provideHistoryPresenter() {
        return new HistoryPresenter(repository);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        setHasOptionsMenu(true);
        adapter = new HistoryAdapter(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.requestData();
    }

    @Override
    public void updateData(List<Event> events) {
        if (events.isEmpty()) {
            emptyHistoryScreenLayout.setVisibility(View.VISIBLE);
            return;
        }
        emptyHistoryScreenLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.updateList(events);
    }

    @Override
    public void showEmptyHistory() {
        emptyHistoryScreenLayout.setVisibility(View.VISIBLE);
        hideLoading();
    }

    @Override
    public void openDetailEvent(String id) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(EVENT_ID, id);
        startActivity(intent);
    }

    @Override
    public void downloadReport(String id) {
        presenter.downloadReport(id);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }
}

