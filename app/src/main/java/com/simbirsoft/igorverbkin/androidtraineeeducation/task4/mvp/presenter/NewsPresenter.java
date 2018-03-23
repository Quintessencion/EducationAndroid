package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.NewsView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {

    private CompositeDisposable compositeDisposable;
    private boolean isLoading;

    public NewsPresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().bindService();
    }

    public void loadData(JsonReadService service) {
        if (!isLoading) {
            getViewState().clearData();
            compositeDisposable.add(service.getAllEvents()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(s -> changeStateLoading())
                    .doOnTerminate(this::changeStateLoading)
                    .subscribe(getViewState()::updateData, tr ->
                            Logger.d("NewsFragment json exception: " + tr.getMessage())));
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
