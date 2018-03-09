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

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.EventPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.NewsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.FilterActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.EventsAdapter;

import java.util.List;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.DetailActivity.EVENT_ID;

public class NewsFragment extends MvpAppCompatFragment implements NewsView, RecyclerViewClickListener {

    @InjectPresenter EventPresenter presenter;
    private EventsAdapter adapter;

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

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getEvents();
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