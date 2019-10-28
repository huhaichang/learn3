package com.example.huhaichang.learn3.two;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.two.recyclerview.Msg;

import java.util.List;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ViewHolder> {
    private List<Msg> mMsgList;

    //用来绑定控件id itemView为onCreateViewHolder方法的返回值
    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout, rightLayout;
        TextView mtvleftmsg, mtvrightmsg;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.ll_leftLayout);
            rightLayout = itemView.findViewById(R.id.ll_rightLayout);
            mtvleftmsg = itemView.findViewById(R.id.tv_leftMsg);
            mtvrightmsg = itemView.findViewById(R.id.tv_rightMsg);
        }
    }

    public ChattingAdapter(List<Msg> msgList){
        mMsgList =msgList;
    }
    @NonNull
    //绑定布局
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chatting_adapter,parent,false);
        return new ViewHolder(view);
    }
   //开始代码
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //每个位置都对应一个msg对象 都可以获取对应item的内容和知道是否是发送还是接收
     Msg msg = mMsgList.get(position);
     //如果收到消息
     if (msg.getType()==Msg.TYPE_RECEIVED){
         holder.leftLayout.setVisibility(View.VISIBLE);  //显示左边
         holder.rightLayout.setVisibility(View.GONE);    //隐藏右边
         holder.mtvleftmsg.setText(msg.getContent());

     }
     else if(msg.getType()==Msg.TYPE_SEND){
            holder.rightLayout.setVisibility(View.VISIBLE);  //显示右边
            holder.leftLayout.setVisibility(View.GONE);    //隐藏左边
            holder.mtvrightmsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }







}
