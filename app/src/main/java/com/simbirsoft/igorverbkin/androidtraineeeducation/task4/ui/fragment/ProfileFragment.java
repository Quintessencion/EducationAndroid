package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Friend;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.ProfileEditorActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.ProfileFriendsAdapter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.util.ImageHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_EDIT = 1;
    public static final String USER = "user";
    public static final String USER_PREFERENCES = "user_preferences";
    public static final String SAVED_USER = "saved_user";

    @BindView(R.id.image_profile) ImageView photo;
    @BindView(R.id.fio) TextView fio;
    @BindView(R.id.birthday) TextView birthday;
    @BindView(R.id.field_activity) TextView fieldActivity;

    private SharedPreferences preferences;
    private User user;

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        preferences = getActivity().getPreferences(MODE_PRIVATE);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        user = getUser();
        if (user != null) {
            setUserFields(user);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycler_friends);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        ProfileFriendsAdapter adapter = new ProfileFriendsAdapter();

        List<Friend> friends = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            friends.add(new Friend("path://image.example " + i, "Name Example " + i));
        }

        adapter.updateList(friends);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private User getUser() {
        return new Gson().fromJson(preferences.getString(USER_PREFERENCES, ""), User.class);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profile_edit) {
            Intent intent = new Intent(getActivity(), ProfileEditorActivity.class);
            intent.putExtra(USER, getUser());
            startActivityForResult(intent, REQUEST_EDIT);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_EDIT) {
            User user = data.getParcelableExtra(SAVED_USER);
            if (user != null) {
                setUserFields(user);
                saveNewData(user);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setUserFields(User user) {
        if (user.getPhoto() != null && !user.getPhoto().isEmpty()) {
            ImageHelper.setImage(getActivity(), Uri.parse(user.getPhoto()), photo);
        } else {
            photo.setImageResource(R.drawable.user_icon);
        }
        fio.setText((String.format(getString(R.string.fio), user.getSecondName(), user.getFirstName())).trim());
        birthday.setText(user.getBirthday());
        fieldActivity.setText(user.getFieldActivity());
    }

    private void saveNewData(User user) {
        preferences.edit().putString(USER_PREFERENCES, new Gson().toJson(user)).apply();
    }
}
