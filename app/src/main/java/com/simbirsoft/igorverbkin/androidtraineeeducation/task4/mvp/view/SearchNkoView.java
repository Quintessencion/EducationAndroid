package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.NkoEvent;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface SearchNkoView extends MvpView {

    void loadData(List<NkoEvent> nkos);

    void setQueryToSearchView(String query);
}
