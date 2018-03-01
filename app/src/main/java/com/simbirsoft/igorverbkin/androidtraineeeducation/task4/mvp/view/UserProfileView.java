package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface UserProfileView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void fillUserFields(User user);

    void logging();
}
