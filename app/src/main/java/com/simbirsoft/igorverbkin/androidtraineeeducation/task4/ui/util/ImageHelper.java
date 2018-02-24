package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageHelper {

    public static void setImage(Context context, Uri fileUri, ImageView image) {
        Picasso.with(context)
                .load(fileUri)
                .fit()
                .into(image);
    }
}
