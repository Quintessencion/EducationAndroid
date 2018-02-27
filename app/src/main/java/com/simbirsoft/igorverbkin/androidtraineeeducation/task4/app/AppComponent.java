package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.NkoPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RepositoryModule.class})
@Singleton
public interface AppComponent {

    void inject(Repository repository);

    void inject(NkoPresenter nkoPresenter);
}
