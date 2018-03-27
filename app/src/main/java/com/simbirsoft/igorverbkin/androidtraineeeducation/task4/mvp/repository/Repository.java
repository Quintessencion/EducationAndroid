package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import io.realm.RealmObject;

public class Repository {

    private DataBase dataBase;
    private SharedPreferences preferences;

    private Subject<String> queryObservable;
    private Subject<SharedPreferences> prefObservable;

    public Repository(Context context, DataBase dataBase, SharedPreferences preferences) {
        this.dataBase = dataBase;
        this.preferences = preferences;
        queryObservable = PublishSubject.create();
        prefObservable = PublishSubject.create();

        dataBase.firstReadDataFromJson(context, Event.class);
    }

    public <T> Flowable<T> loadObject(Class<T> clazz, String nameObject) {
        return Flowable.fromCallable(() -> new Gson().fromJson(preferences.getString(nameObject, ""), clazz))
                .onErrorReturn(tr -> clazz.newInstance())
                .subscribeOn(Schedulers.io());
    }

    public void saveObject(Object obj, String nameObject) {
        preferences.edit().putString(nameObject, new Gson().toJson(obj)).apply();
        prefObservable.onNext(preferences);
    }

    public void sendVoiceQuery(String query) {
        queryObservable.onNext(query);
    }

    public Observable<String> voiceQuery() {
        return queryObservable
                .subscribeOn(Schedulers.io());
    }

    public Observable<SharedPreferences> getPreferences() {
        return prefObservable
                .subscribeOn(Schedulers.io());
    }

    public <T extends RealmObject> Flowable<T> getItem(Class<T> clazz) {
        return getItem(new EmptyQueryDecorator(), clazz);
    }

    public <T extends RealmObject> Flowable<T> getItem(QueryDecorator decorator, Class<T> clazz) {
        return Flowable.fromCallable(() -> dataBase.getItem(decorator, clazz))
                .subscribeOn(Schedulers.io());
    }

    public <T extends RealmObject> Flowable<List<T>> getItems(Class<T> clazz) {
        return dataBase.getItems(clazz)
                .subscribeOn(Schedulers.io());
    }

    public <T extends RealmObject> Flowable<List<T>> getItems(QueryDecorator decorator, Class<T> clazz) {
        return dataBase.getItems(decorator, clazz)
                .subscribeOn(Schedulers.io());
    }
}