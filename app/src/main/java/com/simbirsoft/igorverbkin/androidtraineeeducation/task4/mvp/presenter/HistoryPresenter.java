package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.History;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.HistoryView;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.ProfileFragment.USER_PREFERENCES;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    private Repository repository;
    private CompositeDisposable disposable;

    public HistoryPresenter() {
        repository = App.getComponent().repository();
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachView(HistoryView view) {
        super.attachView(view);
        loadDataUser();
    }

    private void loadDataUser() {
        disposable.add(repository.loadObject(User.class, USER_PREFERENCES)
                .subscribe(user -> {
                    if (user.getHistory() != null) {
                        getEventsByIds(user.getHistory());
                    }
                }));
    }

    private void getEventsByIds(List<History> histories) {
        disposable.add(repository.getEventsByIds(histories).subscribe(getViewState()::updateData));
    }

    public void downloadReport(String id) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        disposable.dispose();
    }
}
