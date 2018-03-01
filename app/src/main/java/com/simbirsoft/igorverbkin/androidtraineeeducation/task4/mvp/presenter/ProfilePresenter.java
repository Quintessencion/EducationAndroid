package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.UserProfileView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.ProfileFragment.USER_PREFERENCES;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<UserProfileView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public ProfilePresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachView(UserProfileView view) {
        super.attachView(view);
        disposable.add(getUser()
                .subscribe(user -> getViewState().fillUserFields(user), tr -> Logger.d("No user data")));
    }

    @Nullable
    private Flowable<User> getUser() {
        return Flowable.fromCallable(() -> (User) repository.loadObject(User.class, USER_PREFERENCES));
    }

    public void saveDataUser(User user) {
        repository.saveObject(user, USER_PREFERENCES);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        disposable.dispose();
    }
}