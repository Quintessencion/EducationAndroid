package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.NewsView;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class EventPresenter extends MvpPresenter<NewsView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public EventPresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    public void getEvents() {
        disposable.add(repository.getAllEvents().subscribe(getViewState()::updateData));
    }

    public void getEvents(String fundName) {
        disposable.add(repository.getEventsByNameOrganization(fundName).subscribe(getViewState()::updateData));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        disposable.dispose();
    }
}
