package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module;

import android.content.SharedPreferences;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.EventStorage;
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
    Repository provideRepository(SharedPreferences preferences, EventStorage eventStorage) {
        return new Repository(preferences, eventStorage);
    }

    @Provides
    @Nonnull
    @Singleton
    EventStorage provideEventStorage() {
        return new EventStorage();
    }
}
