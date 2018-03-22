package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.CategoryHelp;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<EventDetailView> {

    private Repository repository;

    public DetailPresenter(Repository repository) {
        this.repository = repository;
    }

    public void sendMoney(int sum, User user) {
        saveUser(user);
    }

    public void sendOffer(CategoryHelp type, User user) {
        saveUser(user);
    }

    private void saveUser(User user) {
        repository.saveObject(user, User.class.getName());
    }
}
