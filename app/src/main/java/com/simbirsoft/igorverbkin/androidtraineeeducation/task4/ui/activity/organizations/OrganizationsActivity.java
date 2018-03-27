package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.organizations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.OrganizationsPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.OrganizationView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.OnItemClickListener;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.event.EventsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity.EVENT_ID;

public class OrganizationsActivity extends MvpAppCompatActivity implements OrganizationView, OnItemClickListener {

    public static final String EVENT_FUND_NAME = "event_fund_name";

    @InjectPresenter OrganizationsPresenter presenter;
    @Inject Repository repository;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private EventsAdapter adapter;
    private String fundName;

    @ProvidePresenter
    OrganizationsPresenter provideOrganizationsPresenter() {
        return new OrganizationsPresenter(repository);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_organizations);

        ButterKnife.bind(this);

        fundName = getIntent().getStringExtra(EVENT_FUND_NAME);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new EventsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.loadData(fundName);
    }

    @Override
    public void loadData(List<Event> events) {
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
