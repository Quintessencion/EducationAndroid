package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;

import butterknife.BindView;
import butterknife.ButterKnife;

class NkoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_nko) TextView nameNko;

    public NkoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindView(Event nko) {
        nameNko.setText(nko.getFundName());
    }
}
