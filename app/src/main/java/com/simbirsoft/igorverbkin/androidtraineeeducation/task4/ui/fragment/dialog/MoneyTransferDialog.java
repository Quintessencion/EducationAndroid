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

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.ProfileEditorActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MoneyTransferDialog extends DialogFragment {

    private static final int FIRST_SUM = 100;
    private static final int SECOND_SUM = 500;
    private static final int THIRD_SUM = 1000;
    private static final int FOURTH_SUM = 2000;

    public interface ActionHelp {
        void sendMoney(int sum);
    }

    private ActionHelp actionHelp;
    @BindView(R.id.amount_money_layout) TextInputLayout amountMoneyLayout;
    @BindView(R.id.amount_money) EditText amountMoney;
    @BindView(R.id.first_amount) TextView firstAmount;
    @BindView(R.id.second_amount) TextView secondAmount;
    @BindView(R.id.third_amount) TextView thirdAmount;
    @BindView(R.id.fourth_amount) TextView fourthAmount;

    public static MoneyTransferDialog newInstance() {
        Bundle args = new Bundle();
        MoneyTransferDialog fragment = new MoneyTransferDialog();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_money_transfer, null);
        ButterKnife.bind(this, view);
        firstAmount.performClick();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.transfer, null)
                .setNegativeButton(R.string.cancel, ((dialog, which) -> dismiss()))
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            boolean isCloseDialog;
            try {
                int amount = Integer.parseInt(amountMoney.getText().toString());
                actionHelp.sendMoney(amount);
                isCloseDialog = true;
                showInfo(R.string.thanks_for_you_help);
            } catch (NumberFormatException ex) {
                amountMoneyLayout.setError(" ");
                isCloseDialog = false;
                showInfo(R.string.enter_correct_amount);
            }
            if (isCloseDialog) {
                dismiss();
            }
        });
    }

    private void showInfo(int messageId) {
        Toast.makeText(getActivity(), getResources().getString(messageId), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.first_amount, R.id.second_amount, R.id.third_amount, R.id.fourth_amount})
    public void changeAmount(View view) {
        resetAppearance();
        amountMoneyLayout.setError("");
        amountMoneyLayout.clearFocus();
        switch (view.getId()) {
            case R.id.first_amount:
                setChecked(firstAmount);
                amountMoney.setText(String.valueOf(FIRST_SUM));
                break;
            case R.id.second_amount:
                setChecked(secondAmount);
                amountMoney.setText(String.valueOf(SECOND_SUM));
                break;
            case R.id.third_amount:
                setChecked(thirdAmount);
                amountMoney.setText(String.valueOf(THIRD_SUM));
                break;
            case R.id.fourth_amount:
                setChecked(fourthAmount);
                amountMoney.setText(String.valueOf(FOURTH_SUM));
                break;
        }
    }

    @OnTextChanged(R.id.amount_money)
    public void checkChange() {
        amountMoneyLayout.setError("");
    }

    private void resetAppearance() {
        setUnchecked(firstAmount);
        setUnchecked(secondAmount);
        setUnchecked(thirdAmount);
        setUnchecked(fourthAmount);
    }

    private void setChecked(TextView view) {
        view.setTextColor(getResources().getColor(android.R.color.white));
        view.setBackgroundColor(getResources().getColor(R.color.dark_green));
    }

    private void setUnchecked(TextView view) {
        view.setTextColor(getResources().getColor(R.color.dark_green));
        view.setBackgroundColor(getResources().getColor(android.R.color.white));
    }
}
