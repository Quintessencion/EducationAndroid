package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import io.realm.RealmModel;
import io.realm.RealmQuery;

public interface QueryDecorator {
    <T extends RealmModel> RealmQuery<T> decorateQuery(RealmQuery<T> query);
}
