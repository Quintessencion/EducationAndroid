package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.JsonUtil;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.FilterActivity.FILTERS_PREFERENCES;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NewsFragment.LOAD_RESULT;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NewsFragment.RECEIVER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NewsFragment.RESPONSE_EXTRA_EVENT;

public class JsonReadService extends Service {

    private static final int NUMBER_THREADS = 2;
    private ExecutorService executor;

    private Repository repository;
    private EventBinder binder = new EventBinder();
    private ResultReceiver resultReceiver;

    public JsonReadService() {
        repository = App.getComponent().repository();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        executor = Executors.newFixedThreadPool(NUMBER_THREADS);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        resultReceiver = intent.getParcelableExtra(RECEIVER);
        return binder;
    }

    public void loadData() {
        Logger.d("service loadData() ");
        executor.execute(new JsonFileReader());
    }

    public class EventBinder extends Binder {
        public JsonReadService getService() {
            return JsonReadService.this;
        }
    }


    class JsonFileReader implements Runnable {

        @Override
        public void run() {
            Filter filter = repository.loadFilter(Filter.class, FILTERS_PREFERENCES);
            ArrayList<Event> events = new ArrayList<>();
            try {
                events = JsonUtil.readEventsFromJsonFile(JsonReadService.this, filter.getFilter());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Bundle args = new Bundle();
            args.putParcelableArrayList(RESPONSE_EXTRA_EVENT, events);
            resultReceiver.send(LOAD_RESULT, args);
        }
    }
}
