package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.EventStorage;

import java.util.List;

public class Repository {

    private SharedPreferences preferences;
    private EventStorage eventStorage;

    public Repository(SharedPreferences preferences, EventStorage eventStorage) {
        this.preferences = preferences;
        this.eventStorage = eventStorage;
    }

    public Event getEventById(String id) {
        return eventStorage.getEventById(id);
    }

    public List<Event> getEvents() {
        return eventStorage.getEvents();
    }

    public Object loadObject(Class<?> clazz, String nameObject) {
        return new Gson().fromJson(preferences.getString(nameObject, ""), clazz);
    }

    public void saveObject(Object obj, String nameObject) {
        preferences.edit().putString(nameObject, new Gson().toJson(obj)).apply();
    }
}