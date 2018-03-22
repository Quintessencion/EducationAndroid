package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.history;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    private HistoryAdapter.OnItemClickListener listener;

    @BindView(R.id.news_card_view) CardView cardView;
    @BindView(R.id.headline) TextView headline;
    @BindView(R.id.expiration_date) TextView expirationDate;
    @BindView(R.id.description_assistance) TextView descriptionAssistance;
    @BindView(R.id.download_report_btn) Button downloadReportBtn;

    public HistoryViewHolder(View itemView, HistoryAdapter.OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    public void bindView(Event event) {
        headline.setText(event.getEventName());
        expirationDate.setText(DateUtils.getFormatStringDate(itemView.getResources(), event.getStart(), event.getEnd()));
        descriptionAssistance.setText(event.getDescriptionAssistance());

        if (DateUtils.getRemainingDays(event.getEnd()) > 0) {
            downloadReportBtn.getLayoutParams().height = 0;
        } else {
            downloadReportBtn.setOnClickListener(v -> listener.downloadReport(event.getId()));
        }
        itemView.setOnClickListener(v -> listener.openDetailEvent(event.getId()));
    }
}
