package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SwitchAbleView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<SwitchAbleView> {

    private Repository repository;

    public MainActivityPresenter() {
        repository = App.getComponent().repository();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().loadNewsFragment();
    }

    public void switchToNews() {
        getViewState().loadNewsFragment();
    }

    public void switchToSearch() {
        getViewState().loadSearchFragment();
    }

    public void switchToHelp() {
        getViewState().loadHelpFragment();
    }

    public void switchToHistory() {
        getViewState().loadHistoryFragment();
    }

    public void switchToProfile() {
        getViewState().loadProfileFragment();
    }

    public void setQuery(String query) {
        repository.sendVoiceQuery(query);
    }
}
