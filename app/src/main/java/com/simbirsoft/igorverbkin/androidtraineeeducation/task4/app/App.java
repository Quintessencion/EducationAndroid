package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app;

import android.app.Application;
import android.content.Intent;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.component.AppComponent;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.component.DaggerAppComponent;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.ContextModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .assetFile("events.json")
                .build();
        Realm.setDefaultConfiguration(config);

        component = buildComponent();

        startService(new Intent(this, JsonReadService.class));
    }


    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        stopService(new Intent(this, JsonReadService.class));
    }
}
