package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface EventDetailView extends MvpView {

    void fillEventData(Event event);
}

