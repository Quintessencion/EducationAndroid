package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.ImageUtils;

public class ImageSlideAdapter extends PagerAdapter {

    private Context context;
    private String[] photos;

    public ImageSlideAdapter(Context context, String[] photos) {
        this.context = context;
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.length;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView image = new ImageView(context);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ImageUtils.setImage(context, photos[i], image);
        container.addView(image, 0);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        container.removeView((ImageView) obj);
    }
}
