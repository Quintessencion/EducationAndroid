package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.DataBase;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {
    @Provides
    @Nonnull
    @Singleton
    DataBase provideDataBase() {
        return new DataBase();
    }
}
