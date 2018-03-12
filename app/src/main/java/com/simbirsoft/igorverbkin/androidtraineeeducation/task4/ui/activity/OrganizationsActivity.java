package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.OrganizationPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.receiver.EventResultsReceiver;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.OrganizationView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.EventsAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.RecyclerViewClickListener;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity.EVENT_ID;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.MainActivity.LOAD_EVENTS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.MainActivity.RECEIVER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.MainActivity.RESPONSE_EXTRA_EVENTS;

public class OrganizationsActivity extends MvpAppCompatActivity implements OrganizationView,
        RecyclerViewClickListener, EventResultsReceiver.Receiver {

    public static final String EVENT_FUND_NAME = "event_fund_name";

    @InjectPresenter OrganizationPresenter presenter;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private EventsAdapter adapter;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;

    private EventResultsReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_organizations);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new EventsAdapter(this);
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
    }

    @Override
    public void onStart() {
        super.onStart();
        bindService(new Intent(this, JsonReadService.class)
                .putExtra(RECEIVER, receiver), sc, BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        unbindService(sc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public void updateData(List<Event> events) {
        adapter.updateList(events);
    }

    public void loadData() {
        if (bound) {
            jsonService.loadEvents();
        }
    }

    @Override
    public void openDetailEvent(String id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EVENT_ID, id);
        startActivity(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle data) {
        if (resultCode == LOAD_EVENTS) {
            presenter.getEvents(getIntent().getStringExtra(EVENT_FUND_NAME), data.getParcelableArrayList(RESPONSE_EXTRA_EVENTS));
        }
    }
}
