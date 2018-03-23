package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class NewsPresenter extends MvpPresenter<EventsView> {

    private Repository repository;
    private CompositeDisposable compositeDisposable;
    private boolean isLoading;
    private JsonReadService service;

    public NewsPresenter(Repository repository) {
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().bindService();
        subscribeToPref();
    }

    public void loadData(JsonReadService service) {
        this.service = service;
        if (!isLoading) {
            getViewState().clearData();
            compositeDisposable.add(service.getAllEvents()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(s -> changeStateLoading())
                    .doOnTerminate(this::changeStateLoading)
                    .subscribe(getViewState()::updateData, tr ->
                            Logger.d("EventsFragment json exception: " + tr.getMessage())));
        }
    }

    private void subscribeToPref() {
        repository.getPreferences()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(preferences -> loadData(service));
    }

    private void changeStateLoading() {
        isLoading = !isLoading;
        if (isLoading) {
            getViewState().showLoading();
        } else {
            getViewState().hideLoading();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewState().unbindService();
        compositeDisposable.clear();
    }
}
