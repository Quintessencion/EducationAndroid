package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.nko;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.organizations.OrganizationsActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.BaseSearchFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.OnItemClickListener;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.organizations.OrganizationsActivity.EVENT_FUND_NAME;

public class NkoFragment extends BaseSearchFragment implements OnItemClickListener {

    @InjectPresenter NkoPresenter presenter;
    @Inject Repository repository;

    @BindView(R.id.not_found_view) View notFoundView;
    @BindView(R.id.request_view) View resultNkoView;
    @BindView(R.id.count_results) TextView countResult;
    @BindView(R.id.keywords) TextView keywords;

    private RecyclerView recyclerView;
    private NkoAdapter adapter;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;

    private CompositeDisposable compositeDisposable;

    @ProvidePresenter
    NkoPresenter provideNkoPresenter() {
        return new NkoPresenter(repository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        compositeDisposable = new CompositeDisposable();
        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                jsonService = ((JsonReadService.EventBinder) service).getService();
                bound = true;
                loadEvents("");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nko, container, false);

        ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen.bottom_padding));
        adapter = new NkoAdapter(this);
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
        if (!bound) {
            getActivity().unbindService(sc);
        }
        compositeDisposable.clear();
    }

    public void loadEvents(String query) {
        if (bound) {
            jsonService.getEventsByQuery(query).subscribe(this::loadEvents, tr ->
                    Logger.d("NkoFragment json exception: " + tr.getMessage()));
        }
    }

    public void loadEvents(List<String> events) {
        if (events.size() == 0) {
            notFoundView.setVisibility(View.VISIBLE);
            resultNkoView.setVisibility(View.GONE);
        } else {
            notFoundView.setVisibility(View.GONE);
            resultNkoView.setVisibility(View.VISIBLE);
            countResult.setText(getResources().getQuantityString(R.plurals.organization_plural, events.size(), events.size()));
            adapter.updateList(events);
            recyclerView.scrollToPosition(0);
        }
    }

    @Override
    protected void setHint(SearchView view) {
        view.setQueryHint(getString(R.string.search_hint));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        keywords.setText(query);
        loadEvents(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        keywords.setText(newText);
        loadEvents(newText);
        return false;
    }

    @Override
    public void openDetailEvent(String fundName) {
        Intent intent = new Intent(getActivity(), OrganizationsActivity.class);
        intent.putExtra(EVENT_FUND_NAME, fundName);
        startActivity(intent);
    }
}
