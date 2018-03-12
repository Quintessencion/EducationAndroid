package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.History;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.JsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.FilterActivity.FILTERS_PREFERENCES;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.ProfileFragment.USER_PREFERENCES;

public class JsonReadService extends Service {

    private Repository repository;
    private EventBinder binder = new EventBinder();

    public JsonReadService() {
        repository = App.getComponent().repository();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public Flowable<List<Event>> getAllEvents() {
        return repository.loadObject(Filter.class, FILTERS_PREFERENCES)
                .map((Function<Filter, List<Event>>) filter ->
                        JsonUtil.readAllEvents(JsonReadService.this, filter.getFilter()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Pair<User, Event>> getEventById(String id) {
        return repository.loadObject(User.class, USER_PREFERENCES)
                .map(user -> new Pair<>(user, JsonUtil.readEventById(JsonReadService.this, id)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<String>> getEventsByQuery(String query) {
        return Flowable.fromCallable(() -> JsonUtil.readEventByQuery(JsonReadService.this, query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Event>> getEventsByFundName(String fundName) {
        return Flowable.fromCallable(() -> JsonUtil.readEventByName(JsonReadService.this, fundName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Event>> getHistory() {
        return repository.loadObject(User.class, USER_PREFERENCES)
                .map((Function<User, List<Event>>) user -> {
                    if (user.getHistory() != null) {
                        Map<String, Event> mapEvents = new HashMap<>();
                        for (Event event : JsonUtil.readAllEvents(JsonReadService.this, new ArrayList<>())) {
                            mapEvents.put(event.getId(), event);
                        }
                        List<Event> historyEvents = new ArrayList<>();
                        for (History history : user.getHistory()) {
                            Event event = mapEvents.get(history.getId()).clone();
                            event.setDescriptionAssistance(history.getDescription());
                            historyEvents.add(event);
                        }
                        return historyEvents;
                    }
                    return Collections.emptyList();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public class EventBinder extends Binder {
        public JsonReadService getService() {
            return JsonReadService.this;
        }
    }
}
