package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Friend;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFriendsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.friend_avatar) ImageView imageAvatar;
    @BindView(R.id.name_friend) TextView friendName;

    ProfileFriendsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindView(Friend friend) {
        friendName.setText(friend.getName());
    }
}