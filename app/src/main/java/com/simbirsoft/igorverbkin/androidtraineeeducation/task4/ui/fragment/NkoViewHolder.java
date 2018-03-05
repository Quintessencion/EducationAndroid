package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class NkoViewHolder extends RecyclerView.ViewHolder {

    private RecyclerViewClickListener listener;
    @BindView(R.id.name_nko) TextView nameNko;

    public NkoViewHolder(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    void bindView(String fundName) {
        nameNko.setText(fundName);
        itemView.setOnClickListener(v -> listener.openDetailEvent(fundName));
    }
}