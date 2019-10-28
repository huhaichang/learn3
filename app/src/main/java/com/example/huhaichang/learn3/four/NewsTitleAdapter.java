package com.example.huhaichang.learn3.four;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

import java.util.ArrayList;
import java.util.List;

public class NewsTitleAdapter extends RecyclerView.Adapter {
    private List<News> mNewsList= new ArrayList<>();
    private OnItemClickListener onItemClickListener;



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_newsTitle);
        }
    }
    public NewsTitleAdapter(List<News> newsList,OnItemClickListener onItemClickListener){
        this.mNewsList =newsList;
        this.onItemClickListener = onItemClickListener;
    }
    public NewsTitleAdapter(OnItemClickListener onItemClickListener){

        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_title_adapter,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        News news =mNewsList.get(position);
        ((ViewHolder)holder).mTvTitle.setText(news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
    public interface OnItemClickListener {
        void onClick(int pos);
    }
}
