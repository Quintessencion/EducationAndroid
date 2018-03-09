package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util;

import android.content.res.Resources;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

public class DateUtils {

    private static final DateTimeFormatter dtfEvent = DateTimeFormatter.ofPattern("dd.MM");
    private static final DateTimeFormatter dtfUser = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    public static long getRemainingDays(LocalDate end) {
        return LocalDate.now().until(end, ChronoUnit.DAYS);
    }

    public static String getFormatStringDate(Resources resources, LocalDate start, LocalDate end) {
        long until = LocalDate.now().until(end, ChronoUnit.DAYS);
        int restDays = until > 0 ? (int) until : 0;
        String plural = resources.getQuantityString(R.plurals.days_plural, restDays, restDays);
        return resources.getString(R.string.rest_days, plural, dtfEvent.format(start), dtfEvent.format(end));
    }

    public static String format(LocalDate date) {
        return dtfUser.format(date);
    }

    public static LocalDate parse(String date) {
        return LocalDate.parse(date, dtfUser);
    }
}
