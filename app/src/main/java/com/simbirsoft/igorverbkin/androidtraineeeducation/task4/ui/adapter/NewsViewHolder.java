package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.News;

class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageNews;
    private TextView newsHeadline, newsContent, expirationDate;

    NewsViewHolder(View itemView) {
        super(itemView);
        imageNews = itemView.findViewById(R.id.image_news);
        newsHeadline = itemView.findViewById(R.id.news_headline);
        newsContent = itemView.findViewById(R.id.news_content);
        expirationDate = itemView.findViewById(R.id.expiration_date);
    }

    void bindView(News card, int position) {

    }
}
