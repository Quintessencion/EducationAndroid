package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.component;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.ContextModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.PreferencesModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.RepositoryModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.EventStorage;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class, PreferencesModule.class})
public interface AppComponent {
    Repository repository();

    EventStorage eventStorage();
}
