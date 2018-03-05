package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.EventDetailActivity.IMAGES;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.EventDetailActivity.POSITION;

public class ImageSwitcherActivity extends AppCompatActivity implements WrapperGestureDetector {

    private static final String PHOTO_POSITION = "photo_position";
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    private GestureDetector gestureDetector;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView title;
    @BindView(R.id.image_switcher) ImageSwitcher switcher;

    private int[] imageIds;
    private int count;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(PHOTO_POSITION);
        } else {
            position = getIntent().getIntExtra(POSITION, 0);
        }
        imageIds = getIntent().getIntArrayExtra(IMAGES);
        count = imageIds.length;

        setColorStatusBar(android.R.color.black);
        customizeToolBar();

        switcher.setBackgroundColor(Color.BLACK);
        switcher.setFactory(() -> {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            return imageView;
        });
        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        switchImage();

        gestureDetector = new GestureDetector(this, this);
    }

    private void setColorStatusBar(int resId) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, resId));
    }

    private void customizeToolBar() {
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PHOTO_POSITION, position);
    }

    private void switchImage() {
        switcher.setImageResource(imageIds[position]);
        title.setText(getString(R.string.count_images, position + 1, count));
    }

    public void setPositionNext() {
        position++;
        if (position == count) {
            position = 0;
        }
        switchImage();
    }

    public void setPositionPrev() {
        position--;
        if (position == -1) {
            position = imageIds.length - 1;
        }
        switchImage();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                setPositionNext();
                switcher.setImageResource(imageIds[position]);
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                setPositionPrev();
                switcher.setImageResource(imageIds[position]);
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }
}
