package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;


import android.content.res.Resources;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final SimpleDateFormat sdfDayMonth = new SimpleDateFormat("dd.MM", new Locale("ru"));

    public static String getFormatStringDate(Resources resources, Date start, Date end) {
        int restDays = getDifferenceDays(end);
        String plural = resources.getQuantityString(R.plurals.days_plural, restDays, restDays);
        return resources.getString(R.string.rest_days, plural, getDayMonth(start), getDayMonth(end));
    }

    private static int getDifferenceDays(Date end) {
        int restDays = (int) ((end.getTime() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24 + 1);
        return restDays > 0 ? restDays : 0;
    }

    private static String getDayMonth(Date date) {
        return sdfDayMonth.format(date);
    }
}
