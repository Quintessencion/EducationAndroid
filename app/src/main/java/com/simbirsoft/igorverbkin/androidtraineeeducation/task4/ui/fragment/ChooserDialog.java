package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

public class ChooserDialog extends DialogFragment {

    public static ChooserDialog newInstance() {
        Bundle args = new Bundle();
        ChooserDialog fragment = new ChooserDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] items = getResources().getStringArray(R.array.items);

        return new AlertDialog.Builder(getActivity())
                .setView(LayoutInflater.from(getActivity()).inflate(R.layout.item_chooser, null))
                .setItems(items,
                        (dialog, item) -> {
                            switch (item) {
                                case 0:
//                                    choosePhotoFromGallery();
                                    break;
                                case 1:
//                                    checkPermissionStorage();
                                    break;
                                case 2:
//                                    deleteImage();
                                    break;
                            }
                        })
                .create();
    }
}
