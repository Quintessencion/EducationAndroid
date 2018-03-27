package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventsView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class NewsPresenter extends MvpPresenter<EventsView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public NewsPresenter(Repository repository) {
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    public void getData() {
        disposable.add(Flowable.combineLatest(
                repository.getItems(Event.class),
                repository.loadObject(Filter.class, Filter.class.getName()),
                (events, filter) -> {
                    if (filter.getFilter().size() != 0) {
                        List<Event> filteredEvens = new ArrayList<>();
                        for (Event event : events) {
                            if (event.getCategoriesHelpList().containsAll(filter.getFilter())) {
                                filteredEvens.add(event);
                            }
                        }
                        return filteredEvens;
                    } else {
                        return events;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> getViewState().showLoading())
                .doOnTerminate(() -> getViewState().hideLoading())
                .subscribe(events -> getViewState().updateData(events)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
