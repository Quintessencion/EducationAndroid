package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.main;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.MainActivityPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.SwitchAbleView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.HelpFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.history.HistoryFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.EventsFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.profile.ProfileFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.search.SearchFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements
        BottomNavigationViewEx.OnNavigationItemSelectedListener, SwitchAbleView {

    public static final int HELP_PAGE = 2;

    @InjectPresenter MainActivityPresenter presenter;
    @Inject Repository repository;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView titleToolbar;
    @BindView(R.id.bot_nav_view) BottomNavigationViewEx bottomNV;
    @BindView(R.id.floating_action_button) FloatingActionButton fab;

    private FragmentManager fm;
    private boolean fabIsPressed;

    @ProvidePresenter
    MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter(repository);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        bottomNV = findViewById(R.id.bot_nav_view);
        bottomNV.setOnNavigationItemSelectedListener(this);
        bottomNV.enableAnimation(false);
        bottomNV.enableShiftingMode(false);
        bottomNV.enableItemShiftingMode(false);
        bottomNV.setIconSize(40, 40);
        bottomNV.setIconsMarginTop(0);

        fab.setOnClickListener(v -> bottomNV.setCurrentItem(HELP_PAGE));
        fab.setClickable(false);

        fm = getSupportFragmentManager();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (fabIsPressed) {
            fab.setImageResource(R.drawable.heart_fab_depressed);
        }
        switch (item.getItemId()) {
            case R.id.menu_news:
                presenter.switchToNews();
                fabIsPressed = false;
                return true;
            case R.id.menu_search:
                presenter.switchToSearch();
                fabIsPressed = false;
                return true;
            case R.id.menu_help:
                fab.setImageResource(R.drawable.heart_fab_pressed);
                presenter.switchToHelp();
                fabIsPressed = true;
                return true;
            case R.id.menu_history:
                presenter.switchToHistory();
                fabIsPressed = false;
                return true;
            case R.id.menu_profile:
                presenter.switchToProfile();
                fabIsPressed = false;
                return true;
        }
        return false;
    }

    @Override
    public void loadNewsFragment() {
        changeFragment(EventsFragment.class, R.string.news);
    }

    @Override
    public void loadSearchFragment() {
        changeFragment(SearchFragment.class, R.string.search);
    }

    @Override
    public void loadHelpFragment() {
        changeFragment(HelpFragment.class, R.string.help);
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
                Logger.e(e.getMessage());
            }
        }

        fm.beginTransaction()
                .replace(R.id.fragment_navigation, fragment, classFragment.getName())
                .addToBackStack(null)
                .commit();
        titleToolbar.setText(titleId);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            presenter.setQuery(intent.getStringExtra(SearchManager.QUERY));
        }
    }
}
