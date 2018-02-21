package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simplealertdialog.SimpleAlertDialog;
import com.simplealertdialog.SimpleAlertDialogFragment;
import com.simplealertdialog.SimpleAlertDialogSupportFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileEditorActivity extends AppCompatActivity implements SimpleAlertDialog.OnItemClickListener {

    private static final int PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int PERMISSIONS_WRITE_STORAGE = 2;
    private static final int REQUEST_STORAGE = 3;
    private static final int REQUEST_CAMERA = 4;
    private static final String TAG = "task";
    private static final String DIALOG_CHOOSER = "dialog_chooser";
    public static final int REQUEST_CODE_ICON_ITEMS = 9;

    @BindView(R.id.image_user) RoundedImageView avatar;
    @BindView(R.id.change_photo) TextView changePhoto;
    @BindView(R.id.second_name) EditText secondName;
    @BindView(R.id.first_name) EditText firstName;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private File photoFile;
    private Uri fileUri;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @OnClick({R.id.image_user, R.id.change_photo})
    public void getImage() {
        new SimpleAlertDialogFragment.Builder()
//                .setTheme(R.style.DialogTheme)
                .setItems(R.array.items, new int[]{
                        R.drawable.upload,
                        R.drawable.camera,
                        R.drawable.delete})
                .create()
                .show(getFragmentManager(), DIALOG_CHOOSER);
    }

    @Override
    public void onItemClick(SimpleAlertDialog dialog, int requestCode, int which) {
        switch (which) {
            case 0:
                getPhotoFromStorage();
                break;
            case 1:
                getPhotoFromCamera();
                break;
            case 2:
                deletePhotoFromAvatar();
                break;
        }
    }

    public void getPhotoFromStorage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_STORAGE);
    }

    public void getPhotoFromCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileEditorActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_WRITE_STORAGE);
        } else {
            checkPermissionCamera();
        }
    }

    public void deletePhotoFromAvatar() {
        avatar.setImageResource(R.drawable.user_icon);
        photoFile = null;
        fileUri = null;
        currentPhotoPath = null;
    }


    private void checkPermissionCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileEditorActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
        } else {
            makePhoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermissionCamera();
                }
                break;
            case PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makePhoto();
                }
                break;
            }
        }
    }

    private void makePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                String imageFileName = "photo_" + System.currentTimeMillis() + "_";
                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
                currentPhotoPath = "file:" + photoFile.getAbsolutePath();
                fileUri = Uri.fromFile(photoFile);
            } catch (IOException ex) {
                Log.d(TAG, ex.getMessage());
            }
            if (photoFile != null) {
                fileUri = FileProvider.getUriForFile(this,
                        "com.simbirsoft.igorverbkin.androidtraineeeducation.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(takePictureIntent, REQUEST_CAMERA);
            }
        }
    }

    private void addPhotoToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(fileUri);
        sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_STORAGE:
                fileUri = data.getData();
                if (fileUri != null) {
                    Cursor cursor = getContentResolver().query(data.getData(),
                            new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    if (cursor == null) {
                        return;
                    }
                    cursor.moveToFirst();
                    photoFile = new File(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    cursor.close();
                    setImage();
                }
                break;
            case REQUEST_CAMERA:
                setImage();
//                addPhotoToGallery();
                break;
        }
    }

    private void setImage() {
        Picasso.with(this)
                .load(fileUri)
                .fit()
                .into(avatar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_edit, menu);
        return true;
    }
}
