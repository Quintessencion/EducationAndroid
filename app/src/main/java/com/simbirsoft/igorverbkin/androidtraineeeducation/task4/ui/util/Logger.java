package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.util.Log;

public class Logger {

    private static final String TAG = "task";

    public static void d(String message) {
        Log.d(TAG, message);
    }

    public static void d(String message, Throwable tr) {
        Log.d(TAG, message, tr);
    }

    public static void e(String message) {
        Log.e(TAG, message);
    }

    public static void e(String message, Throwable tr) {
        Log.d(TAG, message, tr);
    }
}
