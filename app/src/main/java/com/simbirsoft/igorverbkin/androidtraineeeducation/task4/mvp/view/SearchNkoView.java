package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(SingleStateStrategy.class)
public interface SearchNkoView extends MvpView {

    void setQueryToSearchView(String query);
}
