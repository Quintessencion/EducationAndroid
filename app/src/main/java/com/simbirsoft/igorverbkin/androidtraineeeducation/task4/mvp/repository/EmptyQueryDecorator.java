package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository;

import io.realm.RealmModel;
import io.realm.RealmQuery;

public class EmptyQueryDecorator implements QueryDecorator {
    @Override
    public <T extends RealmModel> RealmQuery<T> decorateQuery(RealmQuery<T> query) {
        return query;
    }
}
