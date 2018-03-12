package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.History;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.HistoryView;

import java.util.ArrayList;
import java.util.HashMap;
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

    public void loadDataUser(List<Event> events) {
        disposable.add(repository.loadObject(User.class, USER_PREFERENCES)
                .doOnSubscribe(e -> getViewState().showLoading())
                .subscribe(user -> {
                    if (user.getHistory() != null) {
                        Map<String, Event> mapEvents = new HashMap<>();
                        for (Event event : events) {
                            mapEvents.put(event.getId(), event);
                        }
                        List<Event> historyEvents = new ArrayList<>();
                        for (History history : user.getHistory()) {
                            Event event = mapEvents.get(history.getId()).clone();
                            event.setDescriptionAssistance(history.getDescription());
                            historyEvents.add(event);
                        }
                        getViewState().updateData(historyEvents);
                    } else {
                        getViewState().showEmptyHistory();
                    }
                }));
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
