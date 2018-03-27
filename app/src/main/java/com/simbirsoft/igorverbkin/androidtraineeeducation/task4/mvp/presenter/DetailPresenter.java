package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.Pair;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.CategoryHelp;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.QueryDecorator;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.RealmModel;
import io.realm.RealmQuery;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event.FIELD_ID;

@InjectViewState
public class DetailPresenter extends MvpPresenter<EventDetailView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public DetailPresenter(Repository repository) {
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    public void loadData(String eventId) {
        QueryDecorator decorator = new QueryDecorator() {
            @Override
            public <T extends RealmModel> RealmQuery<T> decorateQuery(RealmQuery<T> query) {
                return query.equalTo(FIELD_ID, eventId);
            }
        };

        disposable.add(Flowable.combineLatest(
                repository.getItem(decorator, Event.class),
                repository.loadObject(User.class, User.class.getName()),
                Pair::new)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pair -> getViewState().updateData(pair.first, pair.second)));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
