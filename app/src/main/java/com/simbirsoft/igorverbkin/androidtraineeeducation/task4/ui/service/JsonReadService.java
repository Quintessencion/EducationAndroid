package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.History;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.DateUtils;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.JsonUtil;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

    public Flowable<List<Event>> getEventByCategory(Category category, boolean isCurrent) {
        return Flowable.fromCallable(() -> JsonUtil.readEventByCategory(JsonReadService.this, category))
                .map(events -> {
                    List<Event> eventByCompleteness = new ArrayList<>();
                    if (isCurrent) {
                        for (Event event : events) {
//                            if (DateUtils.getRemainingDays(event.getEnd()) > 0) {
//                                eventByCompleteness.add(event);
//                            }
                        }
                    } else {
                        for (Event event : events) {
//                            if (DateUtils.getRemainingDays(event.getEnd()) < 0) {
//                                eventByCompleteness.add(event);
//                            }
                        }
                    }
                    return eventByCompleteness;
                })
                .delay(2, TimeUnit.SECONDS) //имитация долгой загрузки
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<String>> getEventsByQuery(String query) {
        return Flowable.fromCallable(() -> JsonUtil.readEventByQuery(JsonReadService.this, query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public class EventBinder extends Binder {
        public JsonReadService getService() {
            return JsonReadService.this;
        }
    }
}
