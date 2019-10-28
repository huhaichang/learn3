package com.example.huhaichang.learn3.eight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.huhaichang.learn3.widget.ToastUtil;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        ToastUtil.showMsg(context,"收到我自定义的广播消息");
        //当有序时表示抵达这边就截断   优先级在manifest设置 android:priority="100";数值大优先级大
        //abortBroadcast();
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
