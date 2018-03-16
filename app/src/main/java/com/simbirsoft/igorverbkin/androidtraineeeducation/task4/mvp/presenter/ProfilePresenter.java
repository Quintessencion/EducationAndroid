package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.UserProfileView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<UserProfileView> {

    @Inject Repository repository;
    private CompositeDisposable disposable;

    public ProfilePresenter() {
        App.getComponent().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadDataUser();
    }

    public void loadDataUser() {
        disposable.add(repository.loadObject(User.class, User.class.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::fillUserFields));
    }

    public void saveDataUser(User user) {
        repository.saveObject(user, User.class.getName());
        loadDataUser();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}