package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.EventStorage;

import java.util.List;

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

    public List<String> getOrganizationsByNameRequest(String query) {
        return eventStorage.getOrganizationsByNameRequest(query);
    }

    public List<Event> getEventsByNameOrganization(String fundName) {
        return eventStorage.getEventsByNameOrganization(fundName);
    }

    public List<Event> getAllEvents() {
        return eventStorage.getAllEvents();
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
        return queryObservable;
    }
}