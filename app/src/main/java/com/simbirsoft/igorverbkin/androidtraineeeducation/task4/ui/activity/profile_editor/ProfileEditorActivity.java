package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.profile_editor;

import android.Manifest;
import android.content.Context;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.makeramen.roundedimageview.RoundedImageView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.ProfilePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.UserProfileView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.DateUtils;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.FileUtils;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.ImageUtils;

import org.threeten.bp.LocalDate;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTouch;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.profile_editor.DatePickerDialog.DateSetter;

public class ProfileEditorActivity extends MvpAppCompatActivity implements DateSetter,
        UserProfileView, PickPhotoDialog.ActionChoose {

    private static final int PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int PERMISSIONS_WRITE_STORAGE = 2;
    private static final int REQUEST_INTERNAL_STORAGE = 3;
    private static final int REQUEST_CAMERA = 4;
    public static final String DIALOG_DATE_PICK = "dialog_pick_date";
    public static final String DIALOG_PHOTO_PICK = "dialog_photo_pick";

    @InjectPresenter(type = PresenterType.WEAK) ProfilePresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_toolbar) TextView titleToolbar;
    @BindView(R.id.image_user) RoundedImageView avatar;
    @BindView(R.id.second_name) EditText secondName;
    @BindView(R.id.first_name) EditText firstName;
    @BindView(R.id.date_birthday) EditText birthday;
    @BindView(R.id.field_activity) EditText fieldActivity;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.phone_number) EditText phone;

    private File photoFile;
    private Uri fileUri;

    private SimpleDateFormat sdf;
    private LocalDate userBirthday;

    private boolean onTouch = false;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        ButterKnife.bind(this);

        titleToolbar.setText(getString(R.string.edit));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void fillUserFields(User user) {
        this.user = user;
        try {
            userBirthday = DateUtils.parse(user.getBirthday());
        } catch (NullPointerException ignored) {
        }

        if (!TextUtils.isEmpty(user.getPhoto())) {
            ImageUtils.setImage(this, user.getPhoto(), avatar);
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
        phone.setText(checkOnEmpty(user.getPhoneNumber()));

        FormatWatcher format = new MaskFormatWatcher(MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER));
        format.installOn(phone);
    }

    private String checkOnEmpty(String text) {
        return TextUtils.isEmpty(text) ? "" : text;
    }

    @OnTouch(R.id.date_birthday)
    public boolean setDate(MotionEvent event) {
        onTouch = true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            pickDate();
        }
        int inType = birthday.getInputType();
        birthday.setInputType(InputType.TYPE_NULL);
        birthday.onTouchEvent(event);
        birthday.setInputType(inType);
        onTouch = false;
        return true;
    }

    @OnFocusChange(R.id.date_birthday)
    public void setDate(View v, boolean hasFocus) {
        if (onTouch) {
            return;
        }
        if (hasFocus) {
            pickDate();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    private void pickDate() {
        DatePickerDialog.newInstance(userBirthday).show(getSupportFragmentManager(), DIALOG_DATE_PICK);
    }

    @Override
    public void setDateBirthday(LocalDate date) {
        userBirthday = date;
        birthday.setText(DateUtils.format(userBirthday));
    }

    @OnClick({R.id.image_user, R.id.change_photo, R.id.blackout_layer})
    public void pickImage(View view) {
        PickPhotoDialog.newInstance().show(getSupportFragmentManager(), DIALOG_PHOTO_PICK);
    }

    @Override
    public void pickPhotoFromStorage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_INTERNAL_STORAGE);
    }

    @Override
    public void pickPhotoCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileEditorActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_WRITE_STORAGE);
        } else {
            checkPermissionCamera();
        }
    }

    @Override
    public void deletePhoto() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_INTERNAL_STORAGE:
                if (data.getData() != null) {
                    fileUri = data.getData();
                    Cursor cursor = getContentResolver().query(fileUri,
                            new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    if (cursor == null) {
                        return;
                    }
                    cursor.moveToFirst();
                    photoFile = new File(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    cursor.close();
                    ImageUtils.setImage(this, fileUri, avatar);
                }
                break;
            case REQUEST_CAMERA:
                ImageUtils.setImage(this, fileUri, avatar);
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
        user.setPhoto(fileUri != null ? fileUri.toString() : "");
        user.setSecondName(secondName.getText().toString().trim());
        user.setFirstName(firstName.getText().toString().trim());
        if (userBirthday != null) {
            user.setBirthday(DateUtils.format(userBirthday));
        }
        user.setFieldActivity(fieldActivity.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());
        user.setEmail(email.getText().toString().trim());
        user.setPhoneNumber(phone.getText().toString());

        presenter.saveDataUser(user);

        finish();
    }
}
