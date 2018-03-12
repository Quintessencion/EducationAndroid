package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.EventsAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.RecyclerViewClickListener;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity.EVENT_ID;

public class OrganizationsActivity extends AppCompatActivity implements RecyclerViewClickListener {

    public static final String EVENT_FUND_NAME = "event_fund_name";

    @BindView(R.id.toolbar) Toolbar toolbar;

    private EventsAdapter adapter;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
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
                loadEventsByFundName((getIntent().getStringExtra(EVENT_FUND_NAME)));
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        bindService(new Intent(this, JsonReadService.class), sc, BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        unbindService(sc);
        compositeDisposable.clear();
    }

    public void loadEventsByFundName(String fundName) {
        if (bound) {
            compositeDisposable.add(jsonService.getEventsByFundName(fundName).subscribe(this::updateData,
                    tr -> Logger.d("OrganizationsActivity json exception: " + tr.getMessage())));
        }
    }

    public void updateData(List<Event> events) {
        adapter.updateList(events);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public void openDetailEvent(String id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EVENT_ID, id);
        startActivity(intent);
    }
}
