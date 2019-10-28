package com.example.huhaichang.learn3.two;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by huhaichang on 2019/5/25.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.LinearRecycleView1> {
    private List<ItemData> mDatas =new ArrayList<ItemData>()
       {
           {          //添加11个对象 分别设置每个对象的mShowText
            add(new ItemData("百度",true));
            add(new ItemData("体育",false));
            add(new ItemData("军事",false));
            add(new ItemData("头条",false));
            add(new ItemData("社会",false));
            add(new ItemData("国内",false));
            add(new ItemData("国际",false));
            add(new ItemData("娱乐",false));
            add(new ItemData("科技",false));
            add(new ItemData("财经",false));
            add(new ItemData("时尚",false));
       }
        };
    private Context mContext;
   private OnItemClickListener mItemClickListener;
    int t=0;  //onBindViewHolder判断是否多次执行 会


   //设置构造函数
    public NewsAdapter(Context context, OnItemClickListener onItemClickListener){
        this.mContext = context;
        this.mItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    //把适配器对应的图传入RecycleView中
    public NewsAdapter.LinearRecycleView1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearRecycleView1(LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false));
    }

    @Override
    public int getItemViewType(int position) {
      return position;
    }
//每一个position至少都会执行一次
//点击位置5 位置5为true其它为false 从位置0到11依次设置颜色下划线
    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.LinearRecycleView1 holder, final int position) {

         //为每个对应位置设置Text=data.mShowText
        ItemData data = mDatas.get(position);

        holder.position = position;
        holder.mText.setText(data.mShowText);
        //data.mSelected通过每一个对象的mSelected 判断颜色
        if (data.mSelected ) {
            holder.mText.setTextColor(mContext.getResources().getColor(R.color.red));
            //显示下划线形状的textView
            holder.mBottonLine.setVisibility(View.VISIBLE);
        } else {
            holder.mText.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            //隐藏下划线形状的textView
            holder.mBottonLine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class LinearRecycleView1 extends  RecyclerView.ViewHolder{
        private  TextView mText;
        private View mBottonLine;
        private int position;

        public LinearRecycleView1(View itemView) {
            super(itemView);
               mText = itemView.findViewById(R.id.tv_7);
               mBottonLine = itemView.findViewById(R.id.tv_8);
               //点击一次 调节每一个对象的mSelected
            //每一个对象通过每一个列表的元素获取
            mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0, len = mDatas.size(); i < len; i++) {
                        //分别得到每个对象对应的mSelected
                        //通过点击事件分别改变11个对象的mSelected
                        //当我点击第五个  那么position=4  那么第5个对象的mSelected为true
                        ItemData data = mDatas.get(i);
                        data.mSelected = (i == position);
                    }
                    notifyDataSetChanged();
                    mItemClickListener.onClick(position);
                }
            });
        }



        }

    public interface OnItemClickListener
    {
        void onClick(int pos);
    }

  class ItemData{
        private String mShowText;
        private boolean mSelected;

      ItemData(String text,boolean mSelected) {
          this.mShowText = text;
          this.mSelected=mSelected;
      }
  }
}

