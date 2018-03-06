package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface EventDetailView extends MvpView {

    void fillEventData(User user, Event event);
}

