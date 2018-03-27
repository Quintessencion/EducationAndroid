package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.CompletenessEventsPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.event.EventsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity.EVENT_ID;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.event_category.EventCategoryActivity.BUNDLE_CATEGORY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.event_category.EventCategoryActivity.BUNDLE_IS_CURRENT;

public class CompletenessEventsFragment extends MvpAppCompatFragment implements EventsView, OnItemClickListener {

    @InjectPresenter CompletenessEventsPresenter presenter;
    @Inject Repository repository;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private EventsAdapter adapter;

    private Category category;
    private boolean isCurrent;

    @ProvidePresenter
    CompletenessEventsPresenter provideCompletenessEventsPresenter() {
        return new CompletenessEventsPresenter(repository);
    }

    public static CompletenessEventsFragment newInstance(Category category, boolean isCurrent) {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_CATEGORY, category);
        args.putBoolean(BUNDLE_IS_CURRENT, isCurrent);
        CompletenessEventsFragment fragment = new CompletenessEventsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        category = (Category) getArguments().getSerializable(BUNDLE_CATEGORY);
        isCurrent = getArguments().getBoolean(BUNDLE_IS_CURRENT);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completeness_events, container, false);

        ButterKnife.bind(this, view);

        prepareRecyclerView();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadData(category.getName(), isCurrent);
    }

    private void prepareRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new EventsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateData(List<Event> events) {
        adapter.updateList(events);
    }

    @Override
    public void openDetailEvent(String id) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(EVENT_ID, id);
        startActivity(intent);
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
