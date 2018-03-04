package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.EventStorage;

import java.util.List;

import io.reactivex.Observable;
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

    public Event getEventById(String id) {
        return eventStorage.getEventById(id);
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


    public Object loadObject(Class<?> clazz, String nameObject) {
        return new Gson().fromJson(preferences.getString(nameObject, ""), clazz);
    }

    public void saveObject(Object obj, String nameObject) {
        preferences.edit().putString(nameObject, new Gson().toJson(obj)).apply();
    }

    public void sendVoiceQuery(String query) {
        queryObservable.onNext(query);
    }

    public Observable<String> voiceQuery() {
        return queryObservable;
    }
}