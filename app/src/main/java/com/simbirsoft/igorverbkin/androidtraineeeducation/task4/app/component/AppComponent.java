package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.component;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.ContextModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.DBModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.PreferencesModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.module.RepositoryModule;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.FilterPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.HistoryPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.filter.FilterActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.main.MainActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.splash.SplashActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.EventsFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.history.HistoryFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.nko.NkoFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.profile.ProfileFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RepositoryModule.class, PreferencesModule.class, DBModule.class})
public interface AppComponent {

    Repository repository();

    void inject(FilterPresenter presenter);

    void inject(HistoryPresenter presenter);

    void inject(NkoPresenter presenter);

    void inject(ProfileFragment fragment);

    void inject(DetailActivity activity);

    void inject(FilterActivity activity);

    void inject(HistoryFragment fragment);

    void inject(MainActivity activity);

    void inject(NkoFragment fragment);

    void inject(EventsFragment fragment);

    void inject(SplashActivity activity);
}
