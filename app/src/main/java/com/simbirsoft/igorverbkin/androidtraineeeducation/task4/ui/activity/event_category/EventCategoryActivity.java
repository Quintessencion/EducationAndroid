package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.event_category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.CompletenessEventsFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.HelpPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventCategoryActivity extends AppCompatActivity {

    public static final String BUNDLE_CATEGORY = "category_id";
    public static final String BUNDLE_IS_CURRENT = "category_current_event";

    @BindView(R.id.title_toolbar) TextView title;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.search_container) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_category);

        ButterKnife.bind(this);

        Category category = (Category) getIntent().getSerializableExtra(BUNDLE_CATEGORY);

        title.setText(category.getName());
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        HelpPageAdapter adapter = new HelpPageAdapter(getSupportFragmentManager());
        adapter.addFragment(CompletenessEventsFragment.newInstance(category, true));
        adapter.addFragment(CompletenessEventsFragment.newInstance(category, false));
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
}
