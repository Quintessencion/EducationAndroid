package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class CompletenessEventsPresenter extends MvpPresenter<EventsView> {

    private CompositeDisposable compositeDisposable;
    private boolean isLoading;

    public CompletenessEventsPresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().bindService();
    }

    public void loadData(JsonReadService service, Category category, boolean isCurrent) {
        if (!isLoading) {
            getViewState().clearData();
            compositeDisposable.add(service.getEventByCategory(category, isCurrent)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(s -> changeStateLoading())
                    .doOnTerminate(this::changeStateLoading)
                    .subscribe(getViewState()::updateData, tr ->
                            Logger.d("CompletenessEventsFragment json exception: " + tr.getMessage())));
        }
    }

    private void changeStateLoading() {
        isLoading = !isLoading;
        if (isLoading) {
            getViewState().showLoading();
        } else {
            getViewState().hideLoading();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewState().unbindService();
        compositeDisposable.clear();
    }
}
