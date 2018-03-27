package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.main.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends MvpAppCompatActivity {

    private Disposable subscribe;

    private static final int SPLASH_DISPLAY_LENGTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onStart() {
        super.onStart();
        subscribe = openNextScreen().delay(SPLASH_DISPLAY_LENGTH, TimeUnit.SECONDS)
                .subscribe(intent -> {
                    startActivity(intent);
                    finish();
                });
    }

    private Flowable<Intent> openNextScreen() {
        return Flowable.fromCallable(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscribe != null) {
            subscribe.dispose();
        }
    }
}
