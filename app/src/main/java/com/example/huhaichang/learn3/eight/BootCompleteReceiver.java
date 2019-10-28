package com.example.huhaichang.learn3.eight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.huhaichang.learn3.widget.ToastUtil;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        ToastUtil.showMsg(context,"欢迎您XXX");
        //静态注册 在Manifest中
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
