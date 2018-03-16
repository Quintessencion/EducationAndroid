package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

import javax.inject.Inject;

@InjectViewState
public class DetailPresenter extends MvpPresenter<EventDetailView> {

    @Inject Repository repository;

    public DetailPresenter() {
        App.getComponent().inject(this);
    }

    public void sendMoney(int sum, User user) {
        saveUser(user);
    }

    public void sendOffer(Category type, User user) {
        saveUser(user);
    }

    private void saveUser(User user) {
        repository.saveObject(user, User.class.getName());
    }
}
