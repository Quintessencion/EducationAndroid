package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NewsPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.filter.FilterActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.event.EventsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity.EVENT_ID;

public class EventsFragment extends MvpAppCompatFragment implements EventsView, OnItemClickListener {

    @InjectPresenter NewsPresenter presenter;
    @Inject Repository repository;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private EventsAdapter adapter;

    @ProvidePresenter
    NewsPresenter provideNewsPresenter() {
        return new NewsPresenter(repository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        setHasOptionsMenu(true);
        adapter = new EventsAdapter(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getData();
    }

    @Override
    public void updateData(List<Event> events) {
        adapter.updateList(events);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.news_filter) {
            startActivity(new Intent(getActivity(), FilterActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openDetailEvent(String id) {
        startActivity(new Intent(getActivity(), DetailActivity.class).putExtra(EVENT_ID, id));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}