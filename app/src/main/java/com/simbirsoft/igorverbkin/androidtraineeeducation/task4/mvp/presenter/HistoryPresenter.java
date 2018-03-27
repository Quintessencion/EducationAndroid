package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.History;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.HistoryView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    private Repository repository;
    private CompositeDisposable compositeDisposable;

    public HistoryPresenter(Repository repository) {
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();
    }

    public void requestData() {
        compositeDisposable.add(repository.loadObject(User.class, User.class.getName())
                .flatMap((Function<User, Flowable<List<Event>>>) user -> {
                    if (user.getHistory() != null) {
                        return loadHistory(user);
                    }
                    return Flowable.fromCallable(Collections::emptyList);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> getViewState().showLoading())
                .doOnTerminate(() -> getViewState().hideLoading())
                .subscribe(events -> getViewState().updateData(events)));
    }

    private Flowable<List<Event>> loadHistory(User user) {
        return repository.getItems(Event.class)
                .map(events -> {
                    Map<String, Event> mapEvents = new HashMap<>();
                    for (Event event : events) {
                        mapEvents.put(event.getId(), event);
                    }
                    List<Event> historyEvents = new ArrayList<>();
                    for (History history : user.getHistory()) {
                        Event event = mapEvents.get(history.getId()).clone();
                        event.setDescriptionAssistance(history.getDescription());
                        historyEvents.add(event);
                    }
                    return historyEvents;
                }).subscribeOn(Schedulers.io());
    }

    public void downloadReport(String id) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
