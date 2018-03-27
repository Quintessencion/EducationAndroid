package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import android.content.Context;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.CheckSpaceUtil;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;


public class DataBase {

    public <T extends RealmObject> void firstReadDataFromJson(Context context, Class<T> clazz) {
        try (Realm realm = Realm.getDefaultInstance()) {
            if (realm.where(clazz).count() == 0) {
                final JSONArray jsonArrayEvents = getJsonArray(context);
                realm.executeTransaction(r -> realm.createAllFromJson(clazz, jsonArrayEvents));
            }
        }
    }

    private JSONArray getJsonArray(Context context) {
        try (InputStream is = context.getAssets().open("events.json")) {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);

            return new JSONObject(new String(buffer)).getJSONArray("events");
        } catch (IOException | JSONException e) {
            Logger.d("Error reading json file: " + e.getMessage());
        }
        return null;
    }

    public <T extends RealmObject> boolean saveItem(T item) {
        List<T> list = new ArrayList<>(1);
        list.add(item);
        return saveItems(list);
    }

    public <T extends RealmObject> boolean saveItems(List<T> list) {
        if (CheckSpaceUtil.getAvailableSpaceInMB() < 10) {
            return false;
        }
        if (list == null || list.isEmpty()) {
            return true;
        }

        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(list);
            realm.commitTransaction();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public <T extends RealmObject> T getItem(QueryDecorator decorator, Class<T> clazz) {
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<T> items = decorator.decorateQuery(realm.where(clazz)).findAll();
            return realm.copyFromRealm(items).get(0);
        }
    }

    public <T extends RealmObject> Flowable<List<T>> getItems(Class<T> clazz) {
        return getItems(new EmptyQueryDecorator(), clazz);
    }

    public <T extends RealmObject> Flowable<List<T>> getItems(QueryDecorator decorator, Class<T> clazz) {
        return Flowable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(decorator.decorateQuery(realm.where(clazz)).findAll());
            }
        });
    }
}
