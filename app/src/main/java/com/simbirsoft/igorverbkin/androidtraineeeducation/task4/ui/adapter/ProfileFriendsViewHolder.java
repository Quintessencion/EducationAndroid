package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Friend;

public class ProfileFriendsViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageAvatar;
    private TextView friendName;

    ProfileFriendsViewHolder(View itemView) {
        super(itemView);
        imageAvatar = itemView.findViewById(R.id.friend_avatar);
        friendName = itemView.findViewById(R.id.name_friend);
    }

    void bindView(Friend friend, int position) {
        friendName.setText(friend.getName());
    }
}