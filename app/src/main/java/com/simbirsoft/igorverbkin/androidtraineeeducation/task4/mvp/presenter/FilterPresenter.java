package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.FilterView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class FilterPresenter extends MvpPresenter<FilterView> {

    @Inject Repository repository;
    private CompositeDisposable disposable;

    public FilterPresenter() {
        App.getComponent().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(repository.loadObject(Filter.class, Filter.class.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::fillUserFilters));
    }

    public void saveDataFilter(Filter filter) {
        repository.saveObject(filter, Filter.class.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
