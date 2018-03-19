package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class NkoViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    @BindView(R.id.name_nko) TextView nameNko;

    public NkoViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    void bindView(String fundName) {
        nameNko.setText(fundName);
        itemView.setOnClickListener(v -> listener.openDetailEvent(fundName));
    }
}
