package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.News;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

class NewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_news) ImageView imageNews;
    @BindView(R.id.news_headline) TextView newsHeadline;
    @BindView(R.id.news_content) TextView newsContent;
    @BindView(R.id.expiration_date) TextView expirationDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));

    NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindView(News news) {
        newsHeadline.setText(news.getTitle());
        newsContent.setText(news.getContent());
        expirationDate.setText(sdf.format(news.getDateExpiration()));
    }
}
