package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.EventPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.receiver.EventResultsReceiver;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.NewsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.FilterActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.EventsAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity.EVENT_ID;

public class NewsFragment extends MvpAppCompatFragment implements NewsView, RecyclerViewClickListener,
        EventResultsReceiver.Receiver {

    public static final String RECEIVER = "app_results_receiver";
    public static final String RESPONSE_EXTRA_EVENT = "response_event";
    public static final int LOAD_RESULT = 1;

    @InjectPresenter EventPresenter presenter;
    private EventsAdapter adapter;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;

    private EventResultsReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        adapter = new EventsAdapter(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                jsonService = ((JsonReadService.EventBinder) service).getService();
                bound = true;
                loadData();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };

        receiver = new EventResultsReceiver(new Handler());
        receiver.setReceiver(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().bindService(new Intent(getActivity(), JsonReadService.class)
                .putExtra(RECEIVER, receiver), sc, BIND_AUTO_CREATE);
    }

    @Override
    public void loadData() {
        if (bound) {
            jsonService.loadData();
        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle data) {
        if (resultCode == LOAD_RESULT) {
            updateData(data.getParcelableArrayList(RESPONSE_EXTRA_EVENT));
        }
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
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(EVENT_ID, id);
        startActivity(intent);
    }
}