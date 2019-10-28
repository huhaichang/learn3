package com.example.huhaichang.learn3.two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huhaichang.learn3.R;

/**
 * Created by huhaichang on 2019/5/24.
 */

public class MyAdapterActivity extends BaseAdapter {
    private  Context mtcontext;
    private LayoutInflater layoutInflater;
    public MyAdapterActivity(Context context){
        this.mtcontext =context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         ViewHolder holder = new ViewHolder();
        if(view == null){
                  view = layoutInflater.inflate(R.layout.activity_gridview_item,null);
                  holder.imageView = view.findViewById(R.id.iv_item);
                  holder.textView = view.findViewById(R.id.tv_2);
                  view.setTag(holder);
        }
        else {
           holder = (ViewHolder) view.getTag();
        }
        //赋值
        holder.textView.setText("花");
        Glide.with(mtcontext).load("https://img01.sogoucdn.com/app/a/100520093/e7d4cac126941b5a-396dcc73e3007ef8-5b4fc7e3bec859871596bf959757a7af.jpg").into(holder.imageView);
        return view;
    }


}
