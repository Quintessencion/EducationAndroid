package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.ProfilePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.UserProfileView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.DatePickerFragment;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.FileUtils;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.ImageHelper;
import com.simplealertdialog.SimpleAlertDialog;
import com.simplealertdialog.SimpleAlertDialogFragment;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.MainActivity.TAG;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.DatePickerFragment.DateSetter;
import static com.simplealertdialog.SimpleAlertDialog.OnItemClickListener;

public class ProfileEditorActivity extends MvpAppCompatActivity implements
        OnItemClickListener, DateSetter, UserProfileView {

    private static final int PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int PERMISSIONS_WRITE_STORAGE = 2;
    private static final int REQUEST_INTERNAL_STORAGE = 3;
    private static final int REQUEST_CAMERA = 4;
    private static final String DIALOG_CHOICE_STORAGE = "dialog_chooser";
    public static final String DIALOG_DATE_PICK = "DialogDate";

    @InjectPresenter ProfilePresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.change_photo) TextView changePhoto;
    @BindView(R.id.image_user) RoundedImageView avatar;
    @BindView(R.id.second_name) EditText secondName;
    @BindView(R.id.first_name) EditText firstName;
    @BindView(R.id.date_birthday) EditText birthday;
    @BindView(R.id.field_activity) EditText fieldActivity;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.phone_number) EditText phoneNumber;

    private File photoFile;
    private Uri fileUri;

    private SimpleDateFormat sdf;
    private Date userBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
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

    @Override
    public void fillUserFields(User user) {
        if (user != null) {
            try {
                userBirthday = sdf.parse(user.getBirthday());
            } catch (NullPointerException | ParseException ignored) {
            }

            if (!TextUtils.isEmpty(user.getPhoto())) {
                ImageHelper.setImage(this, Uri.parse(user.getPhoto()), avatar);
                fileUri = Uri.parse(user.getPhoto());
            } else {
                avatar.setImageResource(R.drawable.user_icon);
            }

            secondName.setText(checkOnEmpty(user.getSecondName()));
            firstName.setText(checkOnEmpty(user.getFirstName()));
            birthday.setText(checkOnEmpty(user.getBirthday()));
            fieldActivity.setText(checkOnEmpty(user.getFieldActivity()));
            password.setText(checkOnEmpty(user.getPassword()));
            email.setText(checkOnEmpty(user.getEmail()));
            phoneNumber.setText(checkOnEmpty(user.getPhoneNumber()));
        }
    }

    @Override
    public void logging() {
        Log.d(TAG, getString(R.string.user_data_empty));
    }

    private String checkOnEmpty(String text) {
        return TextUtils.isEmpty(text) ? "" : text;
    }

    @OnTouch(R.id.date_birthday)
    public boolean setDate(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            DatePickerFragment.newInstance(userBirthday).show(getSupportFragmentManager(), DIALOG_DATE_PICK);
        }
        int inType = birthday.getInputType();
        birthday.setInputType(InputType.TYPE_NULL);
        birthday.onTouchEvent(event);
        birthday.setInputType(inType);
        return true;
    }

    @Override
    public void setDateBirthday(Date date) {
        userBirthday = date;
        birthday.setText(sdf.format(userBirthday));
    }

    @OnClick({R.id.image_user, R.id.change_photo, R.id.blackout_layer})
    public void getImage(View view) {
        new SimpleAlertDialogFragment.Builder()
                .setTheme(R.style.DialogTheme)
                .setItems(R.array.items, new int[]{
                        R.drawable.upload,
                        R.drawable.camera,
                        R.drawable.delete})
                .create()
                .show(getFragmentManager(), DIALOG_CHOICE_STORAGE);
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
        startActivityForResult(intent, REQUEST_INTERNAL_STORAGE);
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
            photoFile = FileUtils.createFile();
            if (photoFile != null) {
                fileUri = FileUtils.getUriFromFile(this, photoFile);
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
            case REQUEST_INTERNAL_STORAGE:
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
                    ImageHelper.setImage(this, fileUri, avatar);
                }
                break;
            case REQUEST_CAMERA:
                ImageHelper.setImage(this, fileUri, avatar);
//                addPhotoToGallery();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit_profile) {
            saveUserData();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUserData() {
        User user = new User();
        user.setPhoto(fileUri != null ? fileUri.toString() : "");
        user.setSecondName(secondName.getText().toString().trim());
        user.setFirstName(firstName.getText().toString().trim());
        if (userBirthday != null) {
            user.setBirthday(sdf.format(userBirthday));
        }
        user.setFieldActivity(fieldActivity.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());
        user.setEmail(email.getText().toString().trim());
        user.setPhoneNumber(phoneNumber.getText().toString());

        presenter.saveDataUser(user);

        finish();
    }
}
