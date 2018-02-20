package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.NkoEvent;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.EventRepository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@InjectViewState
public class NkoPresenter extends MvpPresenter<SearchNkoView> {

    private EventRepository repository;

    public NkoPresenter() {
        repository = EventRepository.newInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().loadData(getRandomRows());
    }

    private List<NkoEvent> getRandomRows() {
        List<NkoEvent> data = repository.getData();
        Random random = new Random();
        List<NkoEvent> randomData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            randomData.add(data.get(random.nextInt(data.size())));
        }
        return randomData;
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

    public void refreshData() {
        getViewState().loadData(getRandomRows());
    }
}
