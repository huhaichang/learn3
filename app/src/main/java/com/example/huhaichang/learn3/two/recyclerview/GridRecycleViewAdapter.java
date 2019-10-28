package com.example.huhaichang.learn3.two.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

/**
 * Created by huhaichang on 2019/5/25.
 */

public class GridRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnItemClickListener omItemClickListener;
    //设置构造函数
    public GridRecycleViewAdapter(Context context, OnItemClickListener onItemClickListener){
        this.mContext = context;
        this.omItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    //把适配器对应的图传入RecycleView中
    public GridRecycleViewAdapter.LinearRecycleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearRecycleView(LayoutInflater.from(mContext).inflate(R.layout.grid_recycleview_item,parent,false));
    }

    //操作ui
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((LinearRecycleView)holder).tv.setText("Hello World  ");
        ((LinearRecycleView)holder).tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                omItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    //自己写个类去标识ui
    class LinearRecycleView extends  RecyclerView.ViewHolder{

        private TextView tv;
        public LinearRecycleView(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_5);
        }
    }
    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
