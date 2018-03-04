package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface SearchNkoView extends MvpView {

    void loadData(List<String> nkos);

    void setQueryToSearchView(String query);
}
