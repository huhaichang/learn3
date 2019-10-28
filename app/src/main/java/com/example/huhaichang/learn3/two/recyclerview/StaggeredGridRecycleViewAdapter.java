package com.example.huhaichang.learn3.two.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.huhaichang.learn3.R;

/**
 * Created by huhaichang on 2019/5/25.
 */

public class StaggeredGridRecycleViewAdapter extends RecyclerView.Adapter<StaggeredGridRecycleViewAdapter.LinearRecycleView> {
   private Context mContext;
   private OnItemClickListener omItemClickListener;
   //设置构造函数
    public StaggeredGridRecycleViewAdapter(Context context, OnItemClickListener onItemClickListener){
        this.mContext = context;
        this.omItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    //把适配器对应的图传入RecycleView中
    public StaggeredGridRecycleViewAdapter.LinearRecycleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearRecycleView(LayoutInflater.from(mContext).inflate(R.layout.staggered_grid_recycleview_item,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull StaggeredGridRecycleViewAdapter.LinearRecycleView holder, final int position) {
        if(position % 2 == 0){
         holder.iv.setImageResource(R.drawable.ic_launcher_background);
        }
         else {
            //holder.iv.setImageResource(R.drawable.icon);
            Glide.with(mContext).load("https://img01.sogoucdn.com/app/a/100520093/e7d4cac126941b5a-396dcc73e3007ef8-5b4fc7e3bec859871596bf959757a7af.jpg").into(holder.iv);
        }

          holder.iv.setOnClickListener(new View.OnClickListener() {
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


    class LinearRecycleView extends  RecyclerView.ViewHolder{

        private ImageView iv;
        public LinearRecycleView(View itemView) {
            super(itemView);
               iv = itemView.findViewById(R.id.iv_2);
        }
    }
    public interface OnItemClickListener {
        void onClick(int pos);
    }

}

