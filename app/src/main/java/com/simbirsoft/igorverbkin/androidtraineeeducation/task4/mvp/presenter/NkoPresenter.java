package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SearchNkoView;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class NkoPresenter extends MvpPresenter<SearchNkoView> {

    private final String EMPTY_QUERY = "";

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
        getOrganizationsByName(EMPTY_QUERY);
    }

    public void refreshData() {
        getOrganizationsByName(EMPTY_QUERY);
    }

    public void getOrganizationsByName(String query) {
        disposable.add(repository.getOrganizationsByNameRequest(query).subscribe(getViewState()::loadData));
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
