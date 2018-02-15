package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.HistoryFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NewsFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        final NewsFragment newsFragment = new NewsFragment();
        final SearchFragment searchFragment = new SearchFragment();
        final HistoryFragment historyFragment = new HistoryFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_navigation, newsFragment)
                .add(R.id.fragment_navigation, searchFragment)
                .add(R.id.fragment_navigation, historyFragment)
                .hide(newsFragment)
                .hide(searchFragment)
                .hide(historyFragment)
                .commit();

        actionBar = getSupportActionBar();

        bottomNavigationView = findViewById(R.id.bot_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_news:
                        fragmentManager.beginTransaction()
                                .show(newsFragment)
                                .hide(searchFragment)
                                .hide(historyFragment)
                                .commit();
                        setTitle(R.string.news);
                        break;
                    case R.id.menu_search:
                        fragmentManager.beginTransaction()
                                .hide(newsFragment)
                                .show(searchFragment)
                                .hide(historyFragment)
                                .commit();
                        setTitle(R.string.search);
                        break;
                    case R.id.menu_history:
                        fragmentManager.beginTransaction()
                                .hide(newsFragment)
                                .hide(searchFragment)
                                .show(historyFragment)
                                .commit();
                        setTitle(R.string.history);
                        break;
                }
                return false;
            }
        });
    }

//    public void setTitle(int titleId) {
//        if (actionBar != null) {
//            actionBar.setTitle(titleId);
//        }
//    }
}
