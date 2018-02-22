package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.ProfileEditorActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.MainActivity.TAG;

public class DatePickerFragment extends DialogFragment {

    public interface DateSetter {
        void setDateBirthday(Date date);
    }

    public static final String EXTRA_DATE = "com.simbirsoft.igorverbkin.androidtraineeeducation.date";
    private static final String ARG_DATE = "date";
    private DatePicker datePicker;
    private DateSetter dateSetter;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dateSetter = (DateSetter) context;
        } catch (ClassCastException e) {
            Log.d(TAG, ProfileEditorActivity.class.getSimpleName() + " can't cast to DateSetter");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) getArguments().getSerializable(ARG_DATE));

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        datePicker = view.findViewById(R.id.dialog_date_picker);
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    int year = datePicker.getYear();
                    int month = datePicker.getMonth();
                    int day = datePicker.getDayOfMonth();
                    dateSetter.setDateBirthday(new GregorianCalendar(year, month, day).getTime());
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> dismiss())
                .create();
    }
}
