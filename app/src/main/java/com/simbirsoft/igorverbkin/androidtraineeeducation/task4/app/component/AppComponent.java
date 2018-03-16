package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.component;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.ContextModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.PreferencesModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.RepositoryModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.DetailPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.FilterPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.HistoryPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.ProfilePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class, PreferencesModule.class})
public interface AppComponent {

    Repository repository();

    void inject(DetailPresenter presenter);

    void inject(FilterPresenter presenter);

    void inject(HistoryPresenter presenter);

    void inject(NkoPresenter presenter);

    void inject(ProfilePresenter presenter);
}
