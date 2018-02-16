package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.HistoryFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NewsFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.ProfileFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "task";

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    private TextView titleToolbar;

    private NewsFragment newsFragment;
    private SearchFragment searchFragment;
    private HistoryFragment historyFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleToolbar = findViewById(R.id.title_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        newsFragment = new NewsFragment();
        searchFragment = new SearchFragment();
        historyFragment = new HistoryFragment();
        profileFragment = new ProfileFragment();

        fragmentManager = getSupportFragmentManager();
        switchFragmentAndTitle(newsFragment, R.string.news);

        bottomNavigationView = findViewById(R.id.bot_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_news:
                return switchFragmentAndTitle(newsFragment, R.string.news);
            case R.id.menu_search:
                return switchFragmentAndTitle(searchFragment, R.string.search);
            case R.id.menu_history:
                return switchFragmentAndTitle(historyFragment, R.string.history);
            case R.id.menu_profile:
                return switchFragmentAndTitle(profileFragment, R.string.profile);
        }
        return false;
    }

    private boolean switchFragmentAndTitle(Fragment fragment, int titleId) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_navigation, fragment)
                .commit();
        titleToolbar.setText(titleId);
        return true;
    }
}
