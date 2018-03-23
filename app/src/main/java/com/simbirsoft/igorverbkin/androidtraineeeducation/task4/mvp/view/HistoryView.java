package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HistoryView extends MvpView, LoadingView, EventsView {

    void updateData(List<Event> events);

    void showEmptyHistory();
}
