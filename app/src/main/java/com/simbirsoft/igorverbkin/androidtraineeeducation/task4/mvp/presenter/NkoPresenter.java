package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@InjectViewState
public class NkoPresenter extends MvpPresenter<SearchNkoView> {

    private Repository repository;

    public NkoPresenter() {
        repository = App.getComponent().repository();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().loadData(repository.getEvents());
    }

//    private List<Event> getShuffleRows() {
//        List<Event> data = repository.getEvents();
//        Collections.shuffle(data);
//        return data;
//    }

    public void filter(String query) {
        List<Event> filteredModelList = new ArrayList<>();

        for (Event nko : repository.getEvents()) {
            String text = nko.getFundName().toLowerCase();
            if (text.contains(query.toLowerCase())) {
                filteredModelList.add(nko);
            }
        }
        getViewState().loadData(filteredModelList);
    }

    public void setQuery(String query) {
        getViewState().setQueryToSearchView(query);
    }

    public void refreshData() {
        getViewState().loadData(repository.getEvents());
    }
}
