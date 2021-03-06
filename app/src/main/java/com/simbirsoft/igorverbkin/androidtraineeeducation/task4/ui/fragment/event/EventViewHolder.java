package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.event;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.OnItemClickListener;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.DateUtils;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

class EventViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;

    @BindView(R.id.news_card_view) CardView cardView;
    @BindView(R.id.image_news) ImageView imageNews;
    @BindView(R.id.headline) TextView newsHeadline;
    @BindView(R.id.news_content) TextView newsContent;
    @BindView(R.id.expiration_date) TextView expirationDate;

    EventViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    void bindView(Event event) {
        newsHeadline.setText(event.getEventName());
        newsContent.setText(event.getContent());
        ImageUtils.setImage(itemView.getContext(), event.getPhotos()[0], imageNews);
//        expirationDate.setText(DateUtils.getFormatStringDate(itemView.getResources(), event.getStart(), event.getEnd()));
        itemView.setOnClickListener(v -> listener.openDetailEvent(event.getId()));
    }
}
