package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.EventStorage;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.History;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class Repository {

    private SharedPreferences preferences;
    private EventStorage eventStorage;

    private Subject<String> queryObservable;

    public Repository(SharedPreferences preferences, EventStorage eventStorage) {
        this.preferences = preferences;
        this.eventStorage = eventStorage;
        queryObservable = PublishSubject.create();
    }

    public Flowable<Event> getEventById(String id) {
        return Flowable.fromCallable(() -> eventStorage.getEventById(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<String>> getOrganizationsByNameRequest(String query) {
        return Flowable.fromCallable(() -> eventStorage.getOrganizationsByNameRequest(query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Event>> getEventsByNameOrganization(String fundName) {
        return Flowable.fromCallable(() -> eventStorage.getEventsByNameOrganization(fundName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Event>> getEventsByIds(List<History> histories) {
        return Flowable.fromCallable(() -> {
            Map<String, Event> mapEvents = new HashMap<>();
            for (Event event : eventStorage.getAllEvents()) {
                mapEvents.put(event.getId(), event);
            }

            List<Event> events = new ArrayList<>();
            for (History history : histories) {
                Event event = mapEvents.get(history.getId()).clone();
                event.setDescriptionAssistance(history.getDescription());
                events.add(event);
            }
            return events;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Event>> getAllEvents() {
        return Flowable.fromCallable(() -> eventStorage.getAllEvents())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //preferences  processing
    public <T> Flowable<T> loadObject(Class<T> clazz, String nameObject) {
        return Flowable.fromCallable(() -> new Gson().fromJson(preferences.getString(nameObject, ""), clazz))
                .onErrorReturn(tr -> clazz.newInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void saveObject(Object obj, String nameObject) {
        preferences.edit().putString(nameObject, new Gson().toJson(obj)).apply();
    }

    //voice processing
    public void sendVoiceQuery(String query) {
        queryObservable.onNext(query);
    }

    public Observable<String> voiceQuery() {
        return queryObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}