package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {

    public static void setImage(Context context, String uri, ImageView image) {
        Glide.with(context)
                .load(uri)
                .dontAnimate()
                .into(image);
    }

    public static void setImage(Context context, Uri uri, ImageView image) {
        Glide.with(context)
                .load(uri)
                .dontAnimate()
                .into(image);
    }
}
