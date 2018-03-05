package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Filter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.FilterPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.FilterView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends MvpAppCompatActivity implements FilterView {

    public static final String FILTERS_PREFERENCES = "user_filters_preferences";

    @InjectPresenter FilterPresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView title;
    @BindView(R.id.money_switch) Switch moneySwitch;
    @BindView(R.id.things_switch) Switch thingsSwitch;
    @BindView(R.id.prof_help_switch) Switch profHelpSwitch;
    @BindView(R.id.volunteer_switch) Switch volunteerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ButterKnife.bind(this);

        title.setText(R.string.filter);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void fillUserFilters(Filter filter) {
        moneySwitch.setChecked(filter.isMoneyHelp());
        thingsSwitch.setChecked(filter.isThingsHelp());
        profHelpSwitch.setChecked(filter.isProfHelp());
        volunteerSwitch.setChecked(filter.isVolunteer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit_profile) {
            saveUserFilters();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUserFilters() {
        presenter.saveDataFilter(new Filter(moneySwitch.isChecked(), thingsSwitch.isChecked(),
                profHelpSwitch.isChecked(), volunteerSwitch.isChecked()));
        finish();
    }
}
