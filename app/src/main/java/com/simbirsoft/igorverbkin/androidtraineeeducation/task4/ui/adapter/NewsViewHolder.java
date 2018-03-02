package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.RecyclerViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

class NewsViewHolder extends RecyclerView.ViewHolder {

    private RecyclerViewClickListener listener;

    @BindView(R.id.image_news) ImageView imageNews;
    @BindView(R.id.news_headline) TextView newsHeadline;
    @BindView(R.id.news_content) TextView newsContent;
    @BindView(R.id.expiration_date) TextView expirationDate;

    NewsViewHolder(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    void bindView(Event event) {
        newsHeadline.setText(event.getEventName());
        newsContent.setMaxLines(3);
        newsContent.setText(event.getContent());
//        expirationDate.setText(DateUtils.format(event.getDateExpiration()));
        expirationDate.setText(itemView.getResources().getString(R.string.rest_days, "89", "05.02", "31.05"));
        itemView.setOnClickListener(v -> listener.openDetailEvent(event.getId()));
    }
}
