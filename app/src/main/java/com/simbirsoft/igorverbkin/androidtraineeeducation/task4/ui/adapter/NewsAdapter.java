package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private RecyclerViewClickListener listener;
    private List<Event> data = new ArrayList<>();

    public NewsAdapter(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void updateList(List<Event> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_news, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
