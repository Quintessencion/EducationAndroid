package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class NkoPresenter extends MvpPresenter<SearchNkoView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public NkoPresenter(Repository repository) {
        this.repository = repository;
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
