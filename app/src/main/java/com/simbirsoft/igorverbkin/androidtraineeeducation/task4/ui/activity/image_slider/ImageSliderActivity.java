package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.image_slider;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity.IMAGES;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.detail.DetailActivity.POSITION;

public class ImageSliderActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String PHOTO_POSITION = "photo_position";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView title;
    @BindView(R.id.image_view_pager) ViewPager viewPager;

    private int position;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(PHOTO_POSITION);
        } else {
            position = getIntent().getIntExtra(POSITION, 0);
        }
        String[] photos = getIntent().getStringArrayExtra(IMAGES);
        count = photos.length;

        ImageSlideAdapter adapter = new ImageSlideAdapter(this, photos);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(position);

        setColorStatusBar(android.R.color.black);
        customizeToolbar();
    }

    private void setColorStatusBar(int resId) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, resId));
    }

    private void customizeToolbar() {
        title.setText(getString(R.string.count_images, (position + 1), count));
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
    public void onPageSelected(int position) {
        title.setText(getString(R.string.count_images, (position + 1), count));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PHOTO_POSITION, position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
