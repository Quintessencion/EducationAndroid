package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.NkoEvent;

import java.util.ArrayList;
import java.util.List;

class NkoAdapter extends RecyclerView.Adapter<NkoViewHolder> {

    private List<NkoEvent> data = new ArrayList<>();

    public void updateList(List<NkoEvent> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public NkoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NkoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_nko, parent, false));
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
