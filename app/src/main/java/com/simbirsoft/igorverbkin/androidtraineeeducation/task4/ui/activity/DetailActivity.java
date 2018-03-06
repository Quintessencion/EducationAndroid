package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
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
import com.makeramen.roundedimageview.RoundedImageView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.DetailPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.dialog.HelpDialog;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.dialog.MoneyTransferDialog;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.BECOME_VOLUNTEER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.HELPING_THINGS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.HELP_MONEY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.PROFESSIONAL_HELP;

public class DetailActivity extends MvpAppCompatActivity implements EventDetailView,
        MoneyTransferDialog.ActionHelp, HelpDialog.ActionHelp {

    public static final String DIALOG_HELP = "dialog_help";
    public static final String EVENT_ID = "event_id";
    public static final String POSITION = "position";
    public static final String IMAGES = "images";
    public static final int MAX_NUMB_CONTRIBUTORS = 5;

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

    @BindView(R.id.image_main) ImageView imageMain;
    @BindView(R.id.image_additional_1) ImageView imageAdditional1;
    @BindView(R.id.image_additional_2) ImageView imageAdditional2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ButterKnife.bind(this);
        mailLink.setPaintFlags(mailLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        websiteLink.setPaintFlags(websiteLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        presenter.getEventById(getIntent().getStringExtra(EVENT_ID));

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
    public void fillEventData(User user, Event event) {
        this.user = user;
        title.setText(event.getEventName());
        nameEvent.setText(event.getEventName());
        expirationDate.setText(DateUtils.getFormatStringDate(getResources(), event.getStart(), event.getEnd()));
        fundName.setText(event.getFundName());
        address.setText(event.getAddress());

        imageMain.setOnClickListener(v -> setImageListener(0));
        imageAdditional1.setOnClickListener(v -> setImageListener(1));
        imageAdditional2.setOnClickListener(v -> setImageListener(2));

        StringBuilder eventPhones = new StringBuilder();
        for (String phone : event.getPhones()) {
            eventPhones.append(phone).append("\n");
        }
        this.phone.setText(eventPhones.toString().trim());

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

    private void setImageListener(int position) {
        Intent imageSwitcher = new Intent(this, ImageSwitcherActivity.class);
        imageSwitcher.putExtra(POSITION, position);
        imageSwitcher.putExtra(IMAGES, new int[]{R.drawable.img, R.drawable.cardimage_2, R.drawable.cardimage_3});
        startActivity(new Intent(imageSwitcher));
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

    private void preparationButtons(TypeAssistance[] types) {
        if (types == null) {
            return;
        }
        for (TypeAssistance type : types) {
            if (type.equals(HELPING_THINGS)) {
                enableHelpBtn(HELPING_THINGS, helpThingsBtn, separator1);
            }
            if (type.equals(BECOME_VOLUNTEER)) {
                enableHelpBtn(BECOME_VOLUNTEER, becomeVolunteerBtn, separator2);
            }
            if (type.equals(PROFESSIONAL_HELP)) {
                enableHelpBtn(PROFESSIONAL_HELP, professionalHelpBtn, separator3);
            }
            if (type.equals(HELP_MONEY)) {
                enableHelpBtn(HELP_MONEY, helpMoneyBtn, separator3);
            }
        }
    }

    private void setContributors(int[] contributors) {
        if (contributors == null || contributors.length == 0) {
            layoutContributors.setVisibility(GONE);
            return;
        }
        countContributors.setText(contributors.length > MAX_NUMB_CONTRIBUTORS
                ? getString(R.string.plus, (contributors.length - MAX_NUMB_CONTRIBUTORS)) : "");

        for (int i = 0; i < contributors.length; i++) {
            if (i == MAX_NUMB_CONTRIBUTORS) {
                return;
            }

            RoundedImageView avatar = new RoundedImageView(this);
            avatar.setMaxWidth(R.dimen.width);
            avatar.setMaxHeight(R.dimen.height);
            avatar.setPadding(i > 0 ? (int) getResources().getDimension(R.dimen.start_padding) : 0, 0, 0, 0);
            avatar.setImageResource(contributors[i]);
            avatar.setBorderColor(getResources().getColor(R.color.silver_light));
            avatar.setBorderWidth(R.dimen.border_width);
            avatar.setElevation(MAX_NUMB_CONTRIBUTORS - i);
            avatar.setScaleType(ImageView.ScaleType.FIT_XY);
            avatar.setOval(true);

            layoutAvatars.addView(avatar);
        }
    }

    private void enableHelpBtn(TypeAssistance type, Button button, View separator) {
        DialogFragment dialog;
        button.setVisibility(VISIBLE);
        separator.setVisibility(VISIBLE);

        if (TypeAssistance.HELP_MONEY.equals(type)) {
            dialog = MoneyTransferDialog.newInstance();
        } else {
            dialog = HelpDialog.newInstance(type, user.getPhoneNumber(), user.getEmail(), user.getFieldActivity());
        }
        button.setOnClickListener(v -> dialog.show(getSupportFragmentManager(), DIALOG_HELP));
    }

    @Override
    public void sendMoney(int sum) {
        presenter.sendMoney(sum, user);
    }

    @Override
    public void sendOfferHelp(TypeAssistance type) {
        presenter.sendOffer(type, user);
    }
}
