package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.ProfileFragment.USER_PREFERENCES;

@InjectViewState
public class DetailPresenter extends MvpPresenter<EventDetailView> {

    private Repository repository;

    public DetailPresenter() {
        repository = App.getComponent().repository();
    }

    public void sendMoney(int sum, User user) {
        saveUser(user);
    }

    public void sendOffer(Category type, User user) {
        saveUser(user);
    }

    private void saveUser(User user) {
        repository.saveObject(user, USER_PREFERENCES);
    }
}
