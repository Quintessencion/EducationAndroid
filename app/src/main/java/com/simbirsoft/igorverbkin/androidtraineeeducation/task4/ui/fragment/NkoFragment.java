package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.EventsOrganizationsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.EventsOrganizationsActivity.EVENT_FUND_NAME;

public class NkoFragment extends BaseSearchFragment implements RecyclerViewClickListener {

    @InjectPresenter NkoPresenter presenter;

    @BindView(R.id.not_found_view) View notFoundView;
    @BindView(R.id.request_view) View resultNkoView;
    @BindView(R.id.count_results) TextView countResult;
    @BindView(R.id.keywords) TextView keywords;

    private RecyclerView recyclerView;
    private NkoAdapter adapter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (presenter != null && isVisibleToUser) {
            presenter.refreshData();
        }
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
    protected void setHint(SearchView view) {
        view.setQueryHint(getString(R.string.search_hint));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        keywords.setText(query);
        presenter.getOrganizationsByName(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        keywords.setText(newText);
        presenter.getOrganizationsByName(newText);
        return false;
    }

    @Override
    public void loadData(List<String> nkos) {
        if (nkos.size() == 0) {
            notFoundView.setVisibility(View.VISIBLE);
            resultNkoView.setVisibility(View.GONE);
        } else {
            notFoundView.setVisibility(View.GONE);
            resultNkoView.setVisibility(View.VISIBLE);
            countResult.setText(getResources().getQuantityString(R.plurals.organization_plural, nkos.size(), nkos.size()));
            adapter.updateList(nkos);
            recyclerView.scrollToPosition(0);
        }
    }

    @Override
    public void openDetailEvent(String fundName) {
        Intent intent = new Intent(getActivity(), EventsOrganizationsActivity.class);
        intent.putExtra(EVENT_FUND_NAME, fundName);
        startActivity(intent);
    }
}
