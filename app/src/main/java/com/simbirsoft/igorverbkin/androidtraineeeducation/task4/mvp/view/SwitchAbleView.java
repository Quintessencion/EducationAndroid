package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface SwitchAbleView extends MvpView {

    void loadNewsFragment();

    void loadSearchFragment();

    void loadHelpFragment();

    void loadHistoryFragment();

    void loadProfileFragment();
}
