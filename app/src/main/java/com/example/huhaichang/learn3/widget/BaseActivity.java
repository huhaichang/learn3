package com.example.huhaichang.learn3.widget;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.huhaichang.learn3.eight.LogonActivity;
import com.example.huhaichang.learn3.three.AlertDialogActivity;

/**
 * Created by huhaichang on 2019/7/26.
 */

public class BaseActivity extends AppCompatActivity {
    //让登录后的打开的Activity都成为广播接收者，未关闭的activity进行记录

    private FroceOffBRC froceOffBRC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChange.add(this);
        //实现广播接收者先写一个类继承
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("789");
        froceOffBRC = new FroceOffBRC();
        registerReceiver(froceOffBRC,intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //实现广播接收者先写一个类继承
       /* IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("789");
        froceOffBRC = new FroceOffBRC();
        registerReceiver(froceOffBRC,intentFilter);*/

    }

    @Override
    protected void onPause() {
        super.onPause();
        //不在这个界面的时候就不要开启了
        if(froceOffBRC!=null){
        unregisterReceiver(froceOffBRC);
        froceOffBRC = null;
        }
        Log.d("Content","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //强制下线前已经关闭就不必记录了
        ActivityChange.remove(this);
    }

    class FroceOffBRC extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            //弹出对话框销毁所有活动
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("收到广播必须强制下线");
            //禁止除按钮外的其它点击
            builder.setCancelable(false);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityChange.finishAll();
                    Intent intent = new Intent(context, LogonActivity.class);
                    context.startActivity(intent);
                }
            }).show();

        }
    }
}
