package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.EventDetailPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailActivity extends MvpAppCompatActivity implements EventDetailView {

    public static final String EVENT_ID = "event_id";

    @InjectPresenter EventDetailPresenter presenter;

    @BindView(R.id.mail_link) TextView mailLink;
    @BindView(R.id.website_link) TextView websiteLink;
    @BindView(R.id.toolbar_event) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView title;
    @BindView(R.id.name_event) TextView nameEvent;
    @BindView(R.id.expiration_date) TextView expirationDate;
    @BindView(R.id.charitable_foundation_name) TextView fundName;
    @BindView(R.id.address) TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ButterKnife.bind(this);
        mailLink.setPaintFlags(mailLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        websiteLink.setPaintFlags(websiteLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        String eventId = getIntent().getStringExtra(EVENT_ID);
        presenter.getEventById(eventId);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void sendLetter(String emailAddress) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailAddress});
        startActivity(Intent.createChooser(emailIntent, getString(R.string.send_via)));
    }

    private void startBrowser(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    @Override
    public void fillEventData(Event event) {
        title.setText(event.getEventName());
        nameEvent.setText(event.getEventName());
        fundName.setText(event.getFundName());
        expirationDate.setText(getString(R.string.rest_days, "89", "05.02", "31.05"));

        mailLink.setOnClickListener(v -> sendLetter(event.getEmail()));
        websiteLink.setOnClickListener(v -> startBrowser(event.getWebSite()));
    }
}
