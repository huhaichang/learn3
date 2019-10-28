package com.example.huhaichang.learn3.fourteen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huhaichang.learn3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huhaichang on 2019/8/13.
 */

public class HuaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Listen listen;
    private List<StringTest> list = new ArrayList<>();
    public HuaAdapter(Context context,List<StringTest> list,Listen listen){
        this.context =context;
        this.listen = listen;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linear_recycleview_item3,parent,false);
        //自己写一个类返回
        return new asdsasda(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        StringTest stringTest = list.get(position);
        ((asdsasda)holder).mTv4.setText(stringTest.getName());
        //自己搞图片的话
        if(stringTest.getId()!=11146) {
            Glide.with(context).load(stringTest.getId()).into(((asdsasda)holder).mIV5);
        }else {
            Glide.with(context).load("https://img01.sogoucdn.com/app/a/100520093/e7d4cac126941b5a-396dcc73e3007ef8-5b4fc7e3bec859871596bf959757a7af.jpg").into(((asdsasda) holder).mIV5);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listen.onclick(position);
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface Listen{
        void onclick(int pos);
    }
    class asdsasda extends RecyclerView.ViewHolder{
    private TextView mTv4;
    private ImageView mIV5;
        public asdsasda(View itemView) {
            super(itemView);
            mTv4 =itemView.findViewById(R.id.tv_4);
            mIV5 = itemView.findViewById(R.id.iv_5);
        }
    }
}
