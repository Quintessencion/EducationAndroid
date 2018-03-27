package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.DataBase;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    @Nonnull
    @Singleton
    Repository provideRepository(Context context, DataBase dataBase, SharedPreferences preferences) {
        return new Repository(context, dataBase, preferences);
    }
}
