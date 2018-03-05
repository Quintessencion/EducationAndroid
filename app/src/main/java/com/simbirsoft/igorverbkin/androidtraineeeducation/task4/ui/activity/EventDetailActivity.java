package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.EventDetailPresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.EventDetailView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.BECOME_VOLUNTEER;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.HELPING_THINGS;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.HELP_MONEY;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance.PROFESSIONAL_HELP;

public class EventDetailActivity extends MvpAppCompatActivity implements EventDetailView {

    public static final String EVENT_ID = "event_id";
    public static final String POSITION = "position";
    public static final String IMAGES = "images";

    @InjectPresenter EventDetailPresenter presenter;

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
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
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

        preparationButtons(event.getTypesAssistance());

        setContributors(event.getContributors());
    }

    private void setImageListener(int position) {
        Intent imageSwitcher = new Intent(this, ImageSwitcherActivity.class);
        imageSwitcher.putExtra(POSITION, position);
        imageSwitcher.putExtra(IMAGES, new int[]{R.drawable.img, R.drawable.cardimage_2, R.drawable.cardimage_3});
        startActivity(new Intent(imageSwitcher));
    }

    private void preparationButtons(TypeAssistance[] types) {
        if (types == null) {
            return;
        }
        for (TypeAssistance type : types) {
            if (type.equals(HELPING_THINGS)) {
                enableHelpingThingsBtn();
            }
            if (type.equals(BECOME_VOLUNTEER)) {
                enableBecomeVolunteerBtn();
            }
            if (type.equals(PROFESSIONAL_HELP)) {
                enableProfHelpBtn();
            }
            if (type.equals(HELP_MONEY)) {
                enableHelpMoneyBtn();
            }
        }
    }

    private void setContributors(int[] contributors) {
        if (contributors == null || contributors.length == 0) {
            layoutContributors.setVisibility(GONE);
            return;
        }
        for (int i = 0; i < contributors.length; i++) {
            if (i == 5) {
                return;
            }

            RoundedImageView avatar = new RoundedImageView(this);
            avatar.setMaxWidth(R.dimen.width);
            avatar.setMaxHeight(R.dimen.height);
            avatar.setPadding(i > 0 ? (int) getResources().getDimension(R.dimen.start_padding) : 0, 0, 0, 0);
            avatar.setImageResource(contributors[i]);
            avatar.setBorderColor(getResources().getColor(R.color.silver_light));
            avatar.setBorderWidth(R.dimen.border_width);
            avatar.setElevation(10 - i);
            avatar.setScaleType(ImageView.ScaleType.FIT_XY);
            avatar.setOval(true);
            avatar.mutateBackground(true);
            avatar.setTileModeX(Shader.TileMode.REPEAT);
            avatar.setTileModeY(Shader.TileMode.REPEAT);

            layoutAvatars.addView(avatar);
        }
        countContributors.setText(getString(R.string.plus, contributors.length));
    }

    private void enableHelpingThingsBtn() {
        helpThingsBtn.setVisibility(VISIBLE);
        helpThingsBtn.setOnClickListener(v -> Toast.makeText(this, "Помощь вещами", Toast.LENGTH_SHORT).show());
        separator1.setVisibility(VISIBLE);
    }

    private void enableBecomeVolunteerBtn() {
        becomeVolunteerBtn.setVisibility(VISIBLE);
        becomeVolunteerBtn.setOnClickListener(v -> Toast.makeText(this, "Стать волонтером", Toast.LENGTH_SHORT).show());
        separator2.setVisibility(VISIBLE);
    }

    private void enableProfHelpBtn() {
        professionalHelpBtn.setVisibility(VISIBLE);
        professionalHelpBtn.setOnClickListener(v -> Toast.makeText(this, "Проф. помощь", Toast.LENGTH_SHORT).show());
        separator3.setVisibility(VISIBLE);
    }

    private void enableHelpMoneyBtn() {
        helpMoneyBtn.setVisibility(VISIBLE);
        helpMoneyBtn.setOnClickListener(v -> Toast.makeText(this, "Помощь деньгами", Toast.LENGTH_SHORT).show());
        separator3.setVisibility(VISIBLE);
    }
}
