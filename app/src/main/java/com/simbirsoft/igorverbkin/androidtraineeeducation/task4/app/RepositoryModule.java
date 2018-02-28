package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoryModule {

    private Context context;

    RepositoryModule(@Nonnull Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Nonnull
    @Singleton
    Repository provideRepository(@Nonnull SharedPreferences preferences) {
        return new Repository(preferences);
    }
}
