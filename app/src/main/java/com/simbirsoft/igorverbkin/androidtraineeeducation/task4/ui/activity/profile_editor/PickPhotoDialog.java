package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.profile_editor;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickPhotoDialog extends DialogFragment {

    @BindView(R.id.make_photo_btn) Button makePhotoBtn;
    @BindView(R.id.pick_photo_btn) Button pickPhotoBtn;
    @BindView(R.id.delete_btn) Button deleteBtn;

    private ActionChoose listener;

    public interface ActionChoose {

        void pickPhotoFromStorage();

        void pickPhotoCamera();

        void deletePhoto();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ActionChoose) context;
        } catch (ClassCastException e) {
            Logger.d(ProfileEditorActivity.class.getSimpleName() + " can't cast to ActionChoose");
        }
    }

    public static PickPhotoDialog newInstance() {
        Bundle args = new Bundle();
        PickPhotoDialog dialog = new PickPhotoDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo_chooser, null);
        ButterKnife.bind(this, view);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    @OnClick({R.id.make_photo_btn, R.id.pick_photo_btn, R.id.delete_btn})
    public void buttonClickListener(View view) {
        switch (view.getId()) {
            case R.id.make_photo_btn:
                listener.pickPhotoFromStorage();
                break;
            case R.id.pick_photo_btn:
                listener.pickPhotoCamera();
                break;
            case R.id.delete_btn:
                listener.deletePhoto();
                break;
        }
        dismiss();
    }
}
