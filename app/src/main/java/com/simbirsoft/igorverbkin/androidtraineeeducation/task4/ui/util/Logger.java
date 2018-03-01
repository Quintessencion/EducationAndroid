package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.util.Log;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.MainActivity.TAG;

public class Logger {

    public static void d(String message) {
        Log.d(TAG, message);
    }

    public static void d(String message, Throwable tr) {
        Log.d(TAG, message, tr);
    }
}
