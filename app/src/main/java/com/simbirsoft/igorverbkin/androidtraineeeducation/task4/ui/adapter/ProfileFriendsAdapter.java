package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Friend;

import java.util.ArrayList;
import java.util.List;

public class ProfileFriendsAdapter extends RecyclerView.Adapter<ProfileFriendsViewHolder> {

    private List<Friend> data = new ArrayList<>();

    public void updateList(List<Friend> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ProfileFriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileFriendsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friends_profile_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(final ProfileFriendsViewHolder holder, final int position) {
        holder.bindView(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
