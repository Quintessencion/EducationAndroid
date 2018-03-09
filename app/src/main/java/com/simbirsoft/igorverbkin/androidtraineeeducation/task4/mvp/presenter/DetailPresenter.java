package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.Pair;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.ProfileFragment.USER_PREFERENCES;

@InjectViewState
public class DetailPresenter extends MvpPresenter<EventDetailView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public DetailPresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    public void getEventById(String id) {
        disposable.add(Flowable.combineLatest(repository.loadObject(
                User.class, USER_PREFERENCES), repository.getEventById(id), Pair::new)
                .subscribe(p -> getViewState().fillEventData(p.first, p.second)));
    }

    private void saveUser(User user) {
        repository.saveObject(user, USER_PREFERENCES);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        disposable.dispose();
    }

    public void sendMoney(int sum, User user) {
        saveUser(user);
    }

    public void sendOffer(TypeAssistance type, User user) {
        saveUser(user);
    }
}
