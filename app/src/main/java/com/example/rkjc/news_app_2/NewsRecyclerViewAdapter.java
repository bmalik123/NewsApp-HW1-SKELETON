package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.graphics.Color.rgb;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    Context mContext;
    ArrayList<NewsItem> mNewsItems;
    Intent intent;
    String urlString;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> newsItems) {
        this.mContext = context;
        this.mNewsItems = newsItems;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, description, date;


        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
        }

        void bind(final int listIndex) {
            title.setText("Title: ".concat(mNewsItems.get(listIndex).getTitle()));
            description.setText("Description:  ".concat(mNewsItems.get(listIndex).getDescription()));
            date.setText("Date:   ".concat(mNewsItems.get(listIndex).getDate().substring(0, mNewsItems.get(listIndex).getDate().indexOf("T"))));
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View view) {
            urlString = (mNewsItems.get(getAdapterPosition()).getUrl());
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            mContext.startActivity(intent);
        }
    }


}



