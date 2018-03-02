package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));

    public static void setFormat(String pattern) {
        sdf.applyPattern(pattern);
    }

    public static String format(Date date) {
        return sdf.format(date);
    }
}
