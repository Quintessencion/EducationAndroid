package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.EventsAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.NkoAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.EventFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NkoFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.SectionsPagerAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity.EVENT_ID;

public class EventCategoryActivity extends AppCompatActivity implements NkoAdapter.OnItemClickListener {

    public static final String BUNDLE_CATEGORY = "category_id";

    @BindView(R.id.title_toolbar) TextView title;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private EventsAdapter adapter;

    private Category category;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_category);

        ButterKnife.bind(this);

        category = (Category) getIntent().getSerializableExtra(BUNDLE_CATEGORY);

        title.setText(category.getName());
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

//        ViewPager viewPager = view.findViewById(R.id.search_container);
//        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager());
//        adapter.addFragment(EventFragment.class);
//        adapter.addFragment(NkoFragment.class);
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = view.findViewById(R.id.search_tabs);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        prepareRecyclerView();

        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                jsonService = ((JsonReadService.EventBinder) service).getService();
                bound = true;
                loadEvent();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };
    }

    private void prepareRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new EventsAdapter(this);
        recyclerView.setAdapter(adapter);
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

    private void loadEvent() {
        if (bound) {
            compositeDisposable.add(jsonService.getEventByCategory(category)
                    .subscribe(this::updateData,
                            tr -> Logger.d("EventCategoryActivity json exception: " + tr.getMessage())));
        }
    }

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
