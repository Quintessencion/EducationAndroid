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
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.help);
        }

        final NewsFragment newsFragment = new NewsFragment();

        bottomNavigationView = findViewById(R.id.bot_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();

                switch (item.getItemId()) {
                    case R.id.menu_news:
                        if (!fm.findFragmentByTag("news").equals(newsFragment)) {
                            fm.beginTransaction()
                                    .add(R.id.fragment_navigation, newsFragment, "news")
                                    .commit();
                        }
                        break;
                    case R.id.menu_search:
                        fm.beginTransaction()
                                .remove(newsFragment)
                                .commit();

                        break;
                }
                return false;
            }
        });
    }
}
