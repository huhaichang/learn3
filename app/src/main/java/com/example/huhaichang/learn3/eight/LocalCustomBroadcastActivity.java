package com.example.huhaichang.learn3.eight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class LocalCustomBroadcastActivity extends AppCompatActivity {
    private Button mBtLocalcustomBC;
    private LocalBroadcastManager localBroadcastManager;
    MyLocalBroadcastReceiver myLocalBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_custom_broadcast);
        mBtLocalcustomBC= findViewById(R.id.bt_localCustomBroadcast);
        //静态注册广播接收者
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("localBCR");
        //发送本地广播
        localBroadcastManager = LocalBroadcastManager.getInstance(this);//本地广播对象获取
        mBtLocalcustomBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("localBCR");//只要广播名字就行不用路径
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        //开启广播接收器
        myLocalBroadcastReceiver =new MyLocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(myLocalBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myLocalBroadcastReceiver);
    }

    class MyLocalBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ToastUtil.showMsg(context,"收到来自本地的广播,无法静态注册");
        }
    }
}
