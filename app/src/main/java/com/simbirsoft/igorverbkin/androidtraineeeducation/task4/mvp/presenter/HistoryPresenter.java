package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.HistoryView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    @Inject Repository repository;
    private CompositeDisposable disposable;

    public HistoryPresenter() {
        App.getComponent().inject(this);
        disposable = new CompositeDisposable();
    }

    public void downloadReport(String id) {

    }
}
