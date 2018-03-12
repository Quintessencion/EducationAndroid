package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.FilterView;

import io.reactivex.disposables.CompositeDisposable;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.FilterActivity.FILTERS_PREFERENCES;

@InjectViewState
public class FilterPresenter extends MvpPresenter<FilterView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public FilterPresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(repository.loadObject(Filter.class, FILTERS_PREFERENCES)
                .subscribe(getViewState()::fillUserFilters));
    }

    public void saveDataFilter(Filter filter) {
        repository.saveObject(filter, FILTERS_PREFERENCES);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
