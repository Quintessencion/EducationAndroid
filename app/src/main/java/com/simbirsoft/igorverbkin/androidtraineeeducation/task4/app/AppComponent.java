package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.ProfilePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class})
public interface AppComponent {

    void inject(NkoPresenter presenter);

    void inject(ProfilePresenter presenter);
}
