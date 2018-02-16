package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.News;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        NewsAdapter adapter = new NewsAdapter();
        List<News> news = new ArrayList<>();
        news.add(new News("TitleExample", "ContentExample"));
        adapter.updateList(news);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
