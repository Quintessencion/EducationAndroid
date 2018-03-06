package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NewsPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.NewsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.EventsAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.RecyclerViewClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity.EVENT_ID;

public class EventsOrganizationsActivity extends MvpAppCompatActivity implements NewsView, RecyclerViewClickListener {

    public static final String EVENT_FUND_NAME = "event_fund_name";

    @InjectPresenter NewsPresenter presenter;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_organizations);

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
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getEvents(getIntent().getStringExtra(EVENT_FUND_NAME));
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

    @Override
    public void openDetailEvent(String id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EVENT_ID, id);
        startActivity(intent);
    }
}
