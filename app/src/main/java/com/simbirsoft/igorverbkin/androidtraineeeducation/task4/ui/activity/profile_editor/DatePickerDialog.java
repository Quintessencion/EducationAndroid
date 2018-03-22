package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.profile_editor;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;

import org.threeten.bp.LocalDate;

public class DatePickerDialog extends DialogFragment {

    public interface DateSetter {
        void setDateBirthday(LocalDate date);
    }

    private static final String ARG_DATE = "arg_date";
    private DatePicker datePicker;
    private DateSetter dateSetter;

    public static DatePickerDialog newInstance(LocalDate date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerDialog fragment = new DatePickerDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dateSetter = (DateSetter) context;
        } catch (ClassCastException e) {
            Logger.d(ProfileEditorActivity.class.getSimpleName() + " can't cast to DateSetter");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LocalDate date = getArguments().getSerializable(ARG_DATE) == null
                ? LocalDate.now()
                : (LocalDate) getArguments().getSerializable(ARG_DATE);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        datePicker = view.findViewById(R.id.dialog_date_picker);
        datePicker.init(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), null);
        datePicker.setMaxDate(System.currentTimeMillis());

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    dateSetter.setDateBirthday(LocalDate.of(
                            datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()));
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> dismiss())
                .create();
    }
}
