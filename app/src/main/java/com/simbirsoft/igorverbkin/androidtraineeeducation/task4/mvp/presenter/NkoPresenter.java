package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.NkoEvent;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.EventRepository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@InjectViewState
public class NkoPresenter extends MvpPresenter<SearchNkoView> {

    private EventRepository repository;

    public NkoPresenter() {
        repository = EventRepository.newInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().loadData(getShuffleRows());
    }

    private List<NkoEvent> getShuffleRows() {
        List<NkoEvent> data = repository.getData();
        Collections.shuffle(data);
        return data;
    }

    public void filter(String query) {
        List<NkoEvent> filteredModelList = new ArrayList<>();

        for (NkoEvent nko : repository.getData()) {
            String text = nko.getName().toLowerCase();
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
        getViewState().loadData(getShuffleRows());
    }
}
