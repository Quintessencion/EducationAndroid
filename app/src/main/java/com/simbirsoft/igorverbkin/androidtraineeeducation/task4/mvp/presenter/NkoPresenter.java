package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class NkoPresenter extends MvpPresenter<SearchNkoView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public NkoPresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        setObserverQuery();
    }

    public void getOrganizationsByName(String query, List<Event> events) {
        Set<String> nameOrganization = new HashSet<>();
        for (Event event : events) {
            if (!TextUtils.isEmpty(query) && event.getFundName().equals(query)) {
                nameOrganization.add(event.getFundName());
            } else {
                nameOrganization.add(event.getFundName());
            }
        }
        List<String> eventsNko = new ArrayList<>();
        eventsNko.addAll(nameOrganization);
        getViewState().loadEvents(eventsNko);
    }

    private void setObserverQuery() {
        disposable.add(repository.voiceQuery()
                .subscribe(getViewState()::setQueryToSearchView, tr -> {
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        disposable.dispose();
    }
}
