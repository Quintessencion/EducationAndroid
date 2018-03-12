package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class OrganizationPresenter extends MvpPresenter<OrganizationView> {

    public void getEvents(String fundName, List<Event> events) {
        List<Event> eventsOrganization = new ArrayList<>();
        for (Event event : events) {
            if (event.getFundName().toLowerCase().contains(fundName.toLowerCase())) {
                eventsOrganization.add(event);
            }
        }
        getViewState().updateData(eventsOrganization);
    }
}
