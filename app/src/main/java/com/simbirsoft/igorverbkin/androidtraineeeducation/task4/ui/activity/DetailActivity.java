package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.DetailPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.dialog.HelpDialog;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.dialog.MoneyTransferDialog;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.service.JsonReadService;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.DateUtils;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.ImageUtils;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.BECOME_VOLUNTEER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.HELPING_THINGS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.HELP_MONEY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Category.PROFESSIONAL_HELP;

public class DetailActivity extends MvpAppCompatActivity implements EventDetailView,
        MoneyTransferDialog.ActionHelp, HelpDialog.ActionHelp {

    public static final String DIALOG_HELP = "dialog_help";
    public static final String EVENT_ID = "event_id";
    public static final String POSITION = "position";
    public static final String IMAGES = "images";
    public static final int MAX_NUMB_CONTRIBUTORS = 5;
    public static final int MAX_NUMB_PHOTO = 3;

    @InjectPresenter DetailPresenter presenter;

    @BindView(R.id.mail_link) TextView mailLink;
    @BindView(R.id.website_link) TextView websiteLink;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView title;
    @BindView(R.id.name_event) TextView nameEvent;
    @BindView(R.id.expiration_date) TextView expirationDate;
    @BindView(R.id.charitable_foundation_name) TextView fundName;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.phone) TextView phone;
    @BindView(R.id.event_content) TextView content;

    @BindViews({R.id.image_main, R.id.image_add_1, R.id.image_add_2}) List<ImageView> images;

    @BindView(R.id.help_things_btn) Button helpThingsBtn;
    @BindView(R.id.become_volunteer_btn) Button becomeVolunteerBtn;
    @BindView(R.id.professional_help_btn) Button professionalHelpBtn;
    @BindView(R.id.help_money_btn) Button helpMoneyBtn;
    @BindView(R.id.separator_1) View separator1;
    @BindView(R.id.separator_2) View separator2;
    @BindView(R.id.separator_3) View separator3;

    @BindView(R.id.layout_contributors) LinearLayout layoutContributors;
    @BindView(R.id.layout_avatars) LinearLayout layoutAvatars;
    @BindView(R.id.count_contributors) TextView countContributors;

    private User user;
    private String eventId;

    private ServiceConnection sc;
    private JsonReadService jsonService;
    private boolean bound;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ButterKnife.bind(this);

        mailLink.setPaintFlags(mailLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        websiteLink.setPaintFlags(websiteLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        eventId = getIntent().getStringExtra(EVENT_ID);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                jsonService = ((JsonReadService.EventBinder) service).getService();
                bound = true;
                loadEvent();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        bindService(new Intent(this, JsonReadService.class), sc, BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        unbindService(sc);
        compositeDisposable.clear();
    }

    private void loadEvent() {
        if (bound) {
            compositeDisposable.add(jsonService.getEventById(eventId)
                    .subscribe(pair -> fillEventData(pair.first, pair.second),
                            tr -> Logger.d("DetailActivity json exception: " + tr.getMessage())));
        }
    }

    private final ButterKnife.Setter<View, String[]> SET_PHOTO = (view, photos, index) -> {
        view.setVisibility(VISIBLE);
        if (index > 2) {
            return;
        }
        Glide.with(this).load(photos[index]).into((ImageView) view);
    };

    private final ButterKnife.Setter<View, String[]> SET_LISTENERS = (view, photos, index) -> {
        view.setOnClickListener(v -> {
            Intent imageSwitcher = new Intent(DetailActivity.this, ImageSliderActivity.class);
            imageSwitcher.putExtra(POSITION, index);
            imageSwitcher.putExtra(IMAGES, photos);
            startActivity(new Intent(imageSwitcher));
        });
    };

    public void fillEventData(User user, Event event) {
        this.user = user;
        title.setPadding(0, 0, (int) getResources().getDimension(R.dimen.padding_end), 0);
        title.setText(event.getEventName());
        nameEvent.setText(event.getEventName());
        expirationDate.setText(DateUtils.getFormatStringDate(getResources(), event.getStart(), event.getEnd()));
        fundName.setText(event.getFundName());
        address.setText(event.getAddress());

        //Set images
        if (event.getPhotos().length >= MAX_NUMB_PHOTO) {
            ButterKnife.apply(images, SET_PHOTO, event.getPhotos());
        } else if (event.getPhotos().length >= 1) {
            images.get(0).setVisibility(VISIBLE);
            Glide.with(this).load(event.getPhotos()[0]).into(images.get(0));
        }
        ButterKnife.apply(images, SET_LISTENERS, event.getPhotos());

        //Set phones
        StringBuilder eventPhones = new StringBuilder();
        for (String phone : event.getPhones()) {
            eventPhones.append(phone).append("\n");
        }
        phone.setText(eventPhones.toString().trim());

        //Set content
        if (TextUtils.isEmpty(event.getContent())) {
            content.setVisibility(GONE);
        } else {
            content.setText(event.getContent());
        }

        mailLink.setOnClickListener(v -> sendLetter(event.getEmail()));
        websiteLink.setOnClickListener(v -> startBrowser(event.getWebSite()));

        setContributors(event.getContributors());
        preparationButtons(event.getTypesAssistance());
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
        startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), getString(R.string.open_via)));
    }

    //set avatars and count(optional)
    private void setContributors(String[] contributors) {
        if (contributors == null || contributors.length == 0) {
            return;
        }
        layoutContributors.setVisibility(VISIBLE);
        countContributors.setText(contributors.length > MAX_NUMB_CONTRIBUTORS
                ? getString(R.string.plus, (contributors.length - MAX_NUMB_CONTRIBUTORS)) : "");

        int upperBound = contributors.length > 5 ? MAX_NUMB_CONTRIBUTORS : contributors.length;
        for (int i = 0; i < upperBound; i++) {
            RoundedImageView avatar = new RoundedImageView(this);
            avatar.setLayoutParams(new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen.width), (int) getResources().getDimension(R.dimen.height)));
            avatar.setPadding(i > 0 ? (int) getResources().getDimension(R.dimen.start_padding) : 0, 0, 0, 0);
            avatar.setBorderColor(getResources().getColor(R.color.silver_light));
            avatar.setBorderWidth(R.dimen.border_width);
            avatar.setElevation(MAX_NUMB_CONTRIBUTORS - i);
            avatar.setScaleType(ImageView.ScaleType.FIT_XY);
            avatar.setOval(true);
            ImageUtils.setImage(this, contributors[i], avatar);

            layoutAvatars.addView(avatar);
        }
    }

    private void preparationButtons(Category[] types) {
        if (types == null) {
            return;
        }
        for (Category type : types) {
            if (type.equals(HELPING_THINGS)) {
                enableHelpBtn(HELPING_THINGS, helpThingsBtn, separator1);
            } else if (type.equals(BECOME_VOLUNTEER)) {
                enableHelpBtn(BECOME_VOLUNTEER, becomeVolunteerBtn, separator2);
            } else if (type.equals(PROFESSIONAL_HELP)) {
                enableHelpBtn(PROFESSIONAL_HELP, professionalHelpBtn, separator3);
            } else if (type.equals(HELP_MONEY)) {
                enableHelpBtn(HELP_MONEY, helpMoneyBtn, separator3);
            }
        }
    }

    private void enableHelpBtn(Category type, Button button, View separator) {
        DialogFragment dialog;
        button.setVisibility(VISIBLE);
        separator.setVisibility(VISIBLE);

        if (Category.HELP_MONEY.equals(type)) {
            dialog = MoneyTransferDialog.newInstance();
        } else {
            dialog = HelpDialog.newInstance(type, user.getPhoneNumber(), user.getEmail(), user.getFieldActivity());
        }
        button.setOnClickListener(v -> dialog.show(getSupportFragmentManager(), DIALOG_HELP));
    }

    @Override
    public void sendMoney(int sum) {
        user.addHistory(eventId, getString(Category.HELP_MONEY.getDescriptionAssistance()) + ": " + sum + " ₽");
        presenter.sendMoney(sum, user);
    }

    @Override
    public void sendOfferHelp(Category type) {
        user.addHistory(eventId, getString(type.getDescriptionAssistance()));
        presenter.sendOffer(type, user);
    }
}
