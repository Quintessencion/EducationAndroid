package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.News;

import java.text.SimpleDateFormat;
import java.util.Locale;

class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageNews;
    private TextView newsHeadline;
    private TextView newsContent;
    private TextView expirationDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));

    NewsViewHolder(View itemView) {
        super(itemView);
        imageNews = itemView.findViewById(R.id.image_news);
        newsHeadline = itemView.findViewById(R.id.news_headline);
        newsContent = itemView.findViewById(R.id.news_content);
        expirationDate = itemView.findViewById(R.id.expiration_date);
    }

    void bindView(News news) {
        newsHeadline.setText(news.getTitle());
        newsContent.setText(news.getContent());
        expirationDate.setText(sdf.format(news.getDateExpiration()));
    }
}
