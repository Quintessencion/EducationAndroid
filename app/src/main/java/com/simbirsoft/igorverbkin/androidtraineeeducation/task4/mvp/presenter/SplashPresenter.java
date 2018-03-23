package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter;

import android.content.res.AssetManager;

import com.arellomobile.mvp.MvpPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.JsonUtil;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SplashView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class SplashPresenter extends MvpPresenter<SplashView> {

    private Repository repository;
    private AssetManager assets;

    public SplashPresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        repository.checkData(Event.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Event>>() {
                    @Override
                    public void accept(List<Event> events) throws Exception {
                        if (events.size() > 0){
                            getViewState().startNextScreen();
                        }
                    }
                });
//        repository.saveItems();
//        readJsonFile();
    }

    public void setAssetsManager(AssetManager assets) {
        this.assets = assets;
    }

    public void readJsonFile() {
        try {
            ArrayList<Event> events = JsonUtil.readAllEvents(assets);
        } catch (JSONException e) {
            Logger.d("Error reading json file: " + e.getMessage());
        }
    }
}
