package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

import java.util.List;

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

    public void getEventById(String id, List<Event> events) {
        disposable.add(repository.loadObject(User.class, USER_PREFERENCES).subscribe(user -> {
            for (Event event : events) {
                if (event.getId().equals(id)) {
                    getViewState().fillEventData(user, event);
                }
            }
        }));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        disposable.dispose();
    }
}
