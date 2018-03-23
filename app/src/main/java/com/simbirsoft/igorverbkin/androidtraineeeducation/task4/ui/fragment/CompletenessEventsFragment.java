package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.CompletenessEventsPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.event.EventsAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity.EVENT_ID;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.event_category.EventCategoryActivity.BUNDLE_CATEGORY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.event_category.EventCategoryActivity.BUNDLE_IS_CURRENT;

public class CompletenessEventsFragment extends MvpAppCompatFragment implements EventsView, OnItemClickListener {

    @InjectPresenter CompletenessEventsPresenter presenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private EventsAdapter adapter;

    private Category category;
    private boolean isCurrent;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;


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

        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                jsonService = ((JsonReadService.EventBinder) service).getService();
                bound = true;
                presenter.loadData(jsonService, category, isCurrent);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };

        return view;
    }

    @Override
    public void bindService() {
        getActivity().bindService(new Intent(getActivity(), JsonReadService.class), sc, BIND_AUTO_CREATE);
    }

    @Override
    public void unbindService() {
        if (bound) {
            getActivity().unbindService(sc);
            bound = false;
        }
    }

    private void prepareRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new EventsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void updateData(List<Event> events) {
        adapter.updateList(events);
    }

    @Override
    public void clearData() {
        adapter.clearList();
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
