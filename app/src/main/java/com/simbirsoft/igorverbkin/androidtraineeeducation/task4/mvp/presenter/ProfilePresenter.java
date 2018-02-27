package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.UserProfileView;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<UserProfileView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }
}
