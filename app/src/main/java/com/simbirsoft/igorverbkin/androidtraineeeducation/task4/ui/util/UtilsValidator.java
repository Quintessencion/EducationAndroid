package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.util.Patterns;

import java.util.regex.Pattern;

public class UtilsValidator {

    private static final Pattern PHONE = Pattern.compile("^\\d{11}$");
    private static final Pattern FIELD_ACTIVITY = Pattern.compile("^[а-яА-Яa-zA-Z][ а-яА-Яa-zA-Z]{1,52}$");

    public static boolean validatePhone(String text) {
        return !PHONE.matcher(text).find();
    }

    public static boolean validateEmail(String text) {
        return !Patterns.EMAIL_ADDRESS.matcher(text).find();
    }

    public static boolean validateFieldActivity(String text) {
        return !FIELD_ACTIVITY.matcher(text).find();
    }
}
