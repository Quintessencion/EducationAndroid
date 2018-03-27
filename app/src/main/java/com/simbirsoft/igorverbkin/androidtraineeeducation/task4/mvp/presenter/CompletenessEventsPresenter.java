package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.QueryDecorator;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventsView;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.RealmModel;
import io.realm.RealmQuery;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event.FIELD_FUND_CATEGORY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event.FIELD_FUND_END_DATE;

@InjectViewState
public class CompletenessEventsPresenter extends MvpPresenter<EventsView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public CompletenessEventsPresenter(Repository repository) {
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    public void loadData(String category, boolean isCurrent) {
        QueryDecorator decorator = new QueryDecorator() {
            @Override
            public <T extends RealmModel> RealmQuery<T> decorateQuery(RealmQuery<T> query) {
                query.equalTo(FIELD_FUND_CATEGORY, category).and();
                if (isCurrent) {
                    query.greaterThanOrEqualTo(FIELD_FUND_END_DATE, new Date());
                } else {
                    query.lessThanOrEqualTo(FIELD_FUND_END_DATE, new Date());
                }
                return query;
            }
        };

        disposable.add(repository.getItems(decorator, Event.class)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> getViewState().showLoading())
                .doOnTerminate(() -> getViewState().hideLoading())
                .subscribe(getViewState()::updateData));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
