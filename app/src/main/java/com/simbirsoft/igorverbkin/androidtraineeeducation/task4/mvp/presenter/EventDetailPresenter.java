package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class EventDetailPresenter extends MvpPresenter<EventDetailView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public EventDetailPresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    public void getEventById(String id) {
        disposable.add(Flowable.fromCallable(() -> repository.getEventById(id))
                .subscribe(getViewState()::fillEventData));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        disposable.dispose();
    }
}
