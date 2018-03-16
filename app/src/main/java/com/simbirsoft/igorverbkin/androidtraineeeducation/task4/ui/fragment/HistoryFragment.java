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
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.HistoryPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.HistoryView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.HistoryAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity.EVENT_ID;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView,
        HistoryAdapter.OnItemClickListener {

    @InjectPresenter HistoryPresenter presenter;
    private HistoryAdapter adapter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.empty_history_screen) RelativeLayout emptyHistoryScreenLayout;
    @BindView(R.id.progress_bar) ProgressBar loading;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        adapter = new HistoryAdapter(this);
        super.onCreate(savedInstanceState);

        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                jsonService = ((JsonReadService.EventBinder) service).getService();
                bound = true;
                loadHistory();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };

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
        getActivity().bindService(new Intent(getActivity(), JsonReadService.class), sc, BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unbindService(sc);
    }

    public void loadHistory() {
        if (bound) {
            showLoading();
            jsonService.getHistory().subscribe(events -> {
                if (events.isEmpty()) {
                    showEmptyHistory();
                } else {
                    updateData(events);
                }
            });
        }
    }

    @Override
    public void updateData(List<Event> events) {
        emptyHistoryScreenLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.updateList(events);
        hideLoading();
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

