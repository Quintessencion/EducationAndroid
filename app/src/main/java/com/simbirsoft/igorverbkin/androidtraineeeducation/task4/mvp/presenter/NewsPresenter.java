package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.NewsView;

@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {

    private Repository repository;

    public NewsPresenter() {
        repository = App.getComponent().repository();
    }

    public void getEvents() {
        getViewState().updateData(repository.getAllEvents());
    }

    public void getEvents(String fundName) {
        getViewState().updateData(repository.getEventsByNameOrganization(fundName));
    }
}
