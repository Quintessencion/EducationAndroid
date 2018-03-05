package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.UserProfileView;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class FilterPresenter extends MvpPresenter<UserProfileView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public FilterPresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    public void saveDataFilter(Filter filter) {

    }
}
