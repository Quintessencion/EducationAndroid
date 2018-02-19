package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.MainActivityPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.MainActivityView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.HistoryFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NewsFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.ProfileFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements
        BottomNavigationViewEx.OnNavigationItemSelectedListener, MainActivityView {

    public static final String TAG = "task";

    @InjectPresenter MainActivityPresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView titleToolbar;
    @BindView(R.id.bot_nav_view) BottomNavigationViewEx bottomNavigationView;
    @BindView(R.id.floating_action_button) FloatingActionButton floatingActionButton;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bot_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setIconSize(40, 40);
        bottomNavigationView.setIconsMarginTop(0);

        fm = getSupportFragmentManager();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_news:
                presenter.switchToNews();
                return true;
            case R.id.menu_search:
                presenter.switchToSearch();
                return true;
            case R.id.menu_history:
                presenter.switchToHistory();
                return true;
            case R.id.menu_profile:
                presenter.switchToProfile();
                return true;
        }
        return false;
    }

    @Override
    public void loadNewsFragment() {
        changeFragment(NewsFragment.class, R.string.news);
    }

    @Override
    public void loadSearchFragment() {
        changeFragment(SearchFragment.class, R.string.search);
    }

    @Override
    public void loadHistoryFragment() {
        changeFragment(HistoryFragment.class, R.string.history);
    }

    @Override
    public void loadProfileFragment() {
        changeFragment(ProfileFragment.class, R.string.profile);
    }

    private void changeFragment(Class classFragment, int titleId) {
        Fragment fragment = fm.findFragmentByTag(classFragment.getName());

        if (fragment == null) {
            try {
                fragment = (Fragment) classFragment.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                Log.d(TAG, e.getMessage());
            }
        }

        fm.beginTransaction()
                .replace(R.id.fragment_navigation, fragment, classFragment.getName())
                .addToBackStack(null)
                .commit();
        titleToolbar.setText(titleId);
    }
}
