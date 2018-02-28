package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app;

import android.app.Application;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.ContextModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class WantHelpApp extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);

        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
