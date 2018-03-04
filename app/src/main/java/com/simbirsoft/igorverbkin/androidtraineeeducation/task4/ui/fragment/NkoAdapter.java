package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.Event;

import java.util.ArrayList;
import java.util.List;

class NkoAdapter extends RecyclerView.Adapter<NkoViewHolder> {

    private RecyclerViewClickListener listener;
    private List<String> data = new ArrayList<>();

    public NkoAdapter(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void updateList(List<String> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public NkoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NkoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_nko, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(NkoViewHolder holder, int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
