package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class NkoPresenter extends MvpPresenter<SearchNkoView> {

    @Inject Repository repository;
    private CompositeDisposable disposable;

    public NkoPresenter() {
        App.getComponent().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        setObserverQuery();
    }

    private void setObserverQuery() {
        disposable.add(repository.voiceQuery()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::setQueryToSearchView, tr -> {
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
