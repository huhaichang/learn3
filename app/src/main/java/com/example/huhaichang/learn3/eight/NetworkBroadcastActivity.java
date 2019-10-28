package com.example.huhaichang.learn3.eight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class NetworkBroadcastActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkBroadCast networkBroadCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_broadcast);
        //注册网络变换广播 接收权限    //也可以在Manifest注册
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //打开广播接收器
        networkBroadCast = new NetworkBroadCast();
        registerReceiver(networkBroadCast,intentFilter);

    }
    class NetworkBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //用于当网络变换后执行的操作（只知道变换 不知道是打开还是关闭）
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
            //当网络变换时通过NetworkInfo实例判断网络是否可用
            if(networkInfo != null && networkInfo.isAvailable()){
                ToastUtil.showMsg(NetworkBroadcastActivity.this,"当前网络可用");
            }
            else {
                ToastUtil.showMsg(NetworkBroadcastActivity.this,"当前网络不可用");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭接收器
        unregisterReceiver(networkBroadCast);
    }
}
