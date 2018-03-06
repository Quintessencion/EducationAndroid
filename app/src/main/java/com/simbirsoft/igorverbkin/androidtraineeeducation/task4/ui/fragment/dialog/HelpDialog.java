package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.ProfileEditorActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.UtilsValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class HelpDialog extends DialogFragment {

    private static final String TYPE = "type";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String FIELD_ACTIVITY = "field_activity";

    @BindView(R.id.phone_layout) TextInputLayout phoneLayout;
    @BindView(R.id.phone) EditText phone;
    @BindView(R.id.email_layout) TextInputLayout emailLayout;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.field_activity_layout) TextInputLayout fieldActivityLayout;
    @BindView(R.id.field_activity) EditText fieldActivity;
    @BindView(R.id.text_prof) TextView textProf;

    private ActionHelp actionHelp;
    private boolean phoneError;
    private boolean emailError;
    private boolean fieldActivityError;
    private boolean isCheckFieldActivity;

    private Observable<String> phoneObservable;
    private Observable<String> emailObservable;
    private Observable<String> fieldActivityObservable;
    private CompositeDisposable compositeDisposable;

    public interface ActionHelp {
        void sendOfferHelp(TypeAssistance type);
    }

    public static HelpDialog newInstance(TypeAssistance type, String phone, String email, String fieldActivity) {
        Bundle args = new Bundle();
        args.putSerializable(TYPE, type);
        args.putString(PHONE, phone);
        args.putString(EMAIL, email);
        args.putString(FIELD_ACTIVITY, fieldActivity);
        HelpDialog fragment = new HelpDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            actionHelp = (ActionHelp) context;
        } catch (ClassCastException e) {
            Logger.d(ProfileEditorActivity.class.getSimpleName() + " can't cast to ActionHelp");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TypeAssistance type = (TypeAssistance) getArguments().getSerializable(TYPE);
        String phone = (String) getArguments().getSerializable(PHONE);
        String email = (String) getArguments().getSerializable(EMAIL);
        String fieldActivity = (String) getArguments().getSerializable(FIELD_ACTIVITY);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_help, null);
        ButterKnife.bind(this, view);

        if (TypeAssistance.PROFESSIONAL_HELP.equals(type)) {
            textProf.setVisibility(View.VISIBLE);
            fieldActivityLayout.setVisibility(View.VISIBLE);
            isCheckFieldActivity = true;
        }

        this.phone.setText(phone);
        setPhoneFormat();
        this.email.setText(email);
        this.fieldActivity.setText(fieldActivity);
        compositeDisposable = new CompositeDisposable();
        createObservables();

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(R.string.cancel, ((dialog, which) -> dismiss()))
                .show();
    }

    public void setPhoneFormat() {
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("+7 (___) ___-__-__");
//        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createTerminated(slots));
        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER));
        formatWatcher.installOn(phone);
    }

    private void createObservables() {
        phoneObservable = RxTextView.textChangeEvents(phone).map(v -> v.text().toString().trim());
        emailObservable = RxTextView.textChangeEvents(email).map(v -> v.text().toString().trim());
        fieldActivityObservable = RxTextView.textChangeEvents(fieldActivity).map(v -> v.text().toString().trim());
    }

    public void subscribe() {
        compositeDisposable.add(phoneObservable.subscribe(this::validatePhone));
        compositeDisposable.add(emailObservable.subscribe(this::validateEmail));
        if (isCheckFieldActivity) {
            compositeDisposable.add(fieldActivityObservable.subscribe(this::validateFieldActivity));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        subscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            if (phoneError) {
                phoneLayout.setError(" ");
                showInfo(R.string.wrong_phone);
            }
            if (emailError) {
                emailLayout.setError(" ");
                showInfo(R.string.wrong_email);
            }
            if (fieldActivityError) {
                fieldActivityLayout.setError(" ");
            }
            if ((!phoneError || !emailError) && !fieldActivityError) {
//                actionHelp.sendOfferHelp();
                showInfo(R.string.thanks_for_you_help);
                dismiss();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    private void showInfo(int messageId) {
        Toast.makeText(getActivity(), getResources().getString(messageId), Toast.LENGTH_SHORT).show();
    }

    public void validatePhone(String text) {
        phoneError = UtilsValidator.validatePhone(text);
        if (!phoneError) {
            phoneLayout.setError("");
        }
    }

    public void validateEmail(String text) {
        emailError = UtilsValidator.validateEmail(text);
        if (!emailError) {
            emailLayout.setError("");
        }
    }

    public void validateFieldActivity(String text) {
        fieldActivityError = UtilsValidator.validateFieldActivity(text);
        if (!fieldActivityError) {
            fieldActivityLayout.setError("");
        }
    }
}
