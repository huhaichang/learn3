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

public class LinearRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private Context mContext;
   private OnItemClickListener omItemClickListener;
    public  LinearRecycleAdapter(Context context,OnItemClickListener onItemClickListener){
        this.mContext = context;
        this.omItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    //每一个位置对应的viewType进行匹配绑定
    public RecyclerView.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
       if(viewType==0){
        return new LinearRecycleView(LayoutInflater.from(mContext).inflate(R.layout.linear_recycleview_item,parent,false));}
        else {
           return new LinearRecycleView1(LayoutInflater.from(mContext).inflate(R.layout.linear_recycleview_item2,parent,false));}
    }
    //给每一个位置 赋值一个viewType
    @Override
    public int getItemViewType(int position) {
        if(position %2 == 0){
        return 0;}
        else {return  1;}
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position)==0){
            ((LinearRecycleView)holder).tv.setText("Hello");
           }
else {
            ((LinearRecycleView1)holder).tv.setText("Hello World ");

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

        private TextView tv;

        public LinearRecycleView(View itemView) {
            super(itemView);
               tv = itemView.findViewById(R.id.tv_3);

        }
    }

    class LinearRecycleView1 extends  RecyclerView.ViewHolder{

        private TextView tv;

        public LinearRecycleView1(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv_4);



        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}

