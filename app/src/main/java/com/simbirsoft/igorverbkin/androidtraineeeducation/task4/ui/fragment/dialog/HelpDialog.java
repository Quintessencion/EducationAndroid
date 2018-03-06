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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.TypeAssistance;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.ProfileEditorActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        if (!TypeAssistance.PROFESSIONAL_HELP.equals(type)) {
            textProf.setVisibility(View.GONE);
            fieldActivityLayout.setVisibility(View.GONE);
        }

        this.phone.setText(phone);
        this.email.setText(email);
        this.fieldActivity.setText(fieldActivity);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    actionHelp.sendOfferHelp(type);
                    showInfo(R.string.thanks_for_you_help);
                })
                .setNegativeButton(R.string.cancel, ((dialog, which) -> dismiss()))
                .show();
    }

    private void showInfo(int messageId) {
        Toast.makeText(getActivity(), getResources().getString(messageId), Toast.LENGTH_SHORT).show();
    }
}
