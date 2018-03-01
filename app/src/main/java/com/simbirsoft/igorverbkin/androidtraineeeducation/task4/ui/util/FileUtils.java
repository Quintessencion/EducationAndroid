package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private static final String AUTHORITY = "com.simbirsoft.igorverbkin.androidtraineeeducation.fileprovider";

    public static File createFile() {
        File photoFile = null;
        try {
            String imageFileName = "photo_" + System.currentTimeMillis() + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException ex) {
            Logger.d(ex.getMessage());
        }
        return photoFile;
    }

    public static Uri getUriFromFile(Context context, File photoFile) {
        return FileProvider.getUriForFile(context, AUTHORITY, photoFile);
    }
}
