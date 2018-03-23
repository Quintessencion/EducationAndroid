package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.CheckSpaceUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;


public class DataBase {

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

    public <E extends RealmModel> E getItem(QueryDecorator decorator, Class<E> clazz) {
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<E> items = decorator.decorateQuery(realm
                    .where(clazz))
                    .findAll();
            if (items.size() == 1) {
                return realm.copyFromRealm(items).get(0);
            }
            return null;
        }
    }

    public <E extends RealmModel> Flowable<List<E>> getItems(Class<E> clazz) {
        return getItems(new EmptyQueryDecorator(), clazz);
    }

    public <E extends RealmModel> Flowable<List<E>> getItems(QueryDecorator decorator, Class<E> clazz) {

        return Flowable.fromCallable(() -> {
            List<E> results = null;
            try (Realm realm = Realm.getDefaultInstance()) {
                results = realm.copyFromRealm(decorator.decorateQuery(realm.where(clazz)).findAll());

                if (results == null) {
                    results = new ArrayList<>();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return results;
        });
    }
}
