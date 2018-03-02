package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

@InjectViewState
public class EventDetailPresenter extends MvpPresenter<EventDetailView> {

    private Repository repository;

    public EventDetailPresenter() {
        repository = App.getComponent().repository();
    }

    public void getEventById(String eventId) {
        getViewState().fillEventData(repository.getEventById(eventId));
    }
}
