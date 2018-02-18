package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.MainActivityView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

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

    public void switchToHistory() {
        getViewState().loadHistoryFragment();
    }

    public void switchToProfile() {
        getViewState().loadProfileFragment();
    }
}
