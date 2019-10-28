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

public class HorRecycleViewAdapter extends RecyclerView.Adapter<HorRecycleViewAdapter.LinearRecycleView> {
   private Context mContext;
   private OnItemClickListener omItemClickListener;
   //设置构造函数
    public HorRecycleViewAdapter(Context context, OnItemClickListener onItemClickListener){
        this.mContext = context;
        this.omItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    //把适配器对应的图传入RecycleView中
    public HorRecycleViewAdapter.LinearRecycleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearRecycleView(LayoutInflater.from(mContext).inflate(R.layout.hori_recycleview_item,parent,false));
    }

    @Override
    public int getItemViewType(int position) {

        if(position  == 0){
            return 0;}
        if(position  == 1){
            return 1;}
        else  return 2;
    }

    @Override
    public void onBindViewHolder(@NonNull HorRecycleViewAdapter.LinearRecycleView holder, final int position) {
        if(getItemViewType(position)==0)holder.tv.setText("Hello World  ");
        else holder.tv.setText("Hello");
          holder.tv.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  omItemClickListener.onClick(position);
              }
          });
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    class LinearRecycleView extends  RecyclerView.ViewHolder{

        private TextView tv;
        public LinearRecycleView(View itemView) {
            super(itemView);
               tv = itemView.findViewById(R.id.tv_4);
        }
    }
    public interface OnItemClickListener {
        void onClick(int pos);
    }

}

