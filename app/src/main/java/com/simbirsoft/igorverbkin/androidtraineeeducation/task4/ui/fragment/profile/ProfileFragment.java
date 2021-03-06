package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.app.App;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Friend;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.User;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.presenter.ProfilePresenter;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.repository.Repository;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.view.UserProfileView;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity.profile_editor.ProfileEditorActivity;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends MvpAppCompatFragment implements UserProfileView {

    @InjectPresenter(type = PresenterType.WEAK) ProfilePresenter presenter;
    @Inject Repository repository;

    @BindView(R.id.image_profile) ImageView photo;
    @BindView(R.id.fio) TextView fio;
    @BindView(R.id.birthday) TextView birthday;
    @BindView(R.id.field_activity) TextView fieldActivity;

    @ProvidePresenter(type = PresenterType.WEAK)
    ProfilePresenter provideProfilePresenter() {
        return new ProfilePresenter(repository);
    }

    public void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void fillUserFields(User user) {
        if (user.getPhoto() != null && !user.getPhoto().isEmpty()) {
            ImageUtils.setImage(getActivity(), user.getPhoto(), photo);
        } else {
            photo.setImageResource(R.drawable.user_icon);
        }
        fio.setText(getString(R.string.fio, checkOnEmpty(user.getSecondName()), checkOnEmpty(user.getFirstName())));
        birthday.setText(user.getBirthday());
        fieldActivity.setText(user.getFieldActivity());
    }

    private String checkOnEmpty(String text) {
        return TextUtils.isEmpty(text) ? "" : text;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profile_edit) {
            startActivity(new Intent(getActivity(), ProfileEditorActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
