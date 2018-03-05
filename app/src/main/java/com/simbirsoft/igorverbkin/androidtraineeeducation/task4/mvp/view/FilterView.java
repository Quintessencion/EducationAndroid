package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FilterView extends MvpView {

    void fillUserFilters(Filter filter);
}
