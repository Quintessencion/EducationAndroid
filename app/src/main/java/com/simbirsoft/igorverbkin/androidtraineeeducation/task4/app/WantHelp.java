package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class WantHelp extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

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
                .repositoryModule(new RepositoryModule())
                .build();
    }
}
