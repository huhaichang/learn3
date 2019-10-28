package com.example.huhaichang.learn3.eight;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class CustomBroadcastActivity extends AppCompatActivity {
    private Button mBtsendCustomBC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_broadcast);
        mBtsendCustomBC = findViewById(R.id.bt_sendCustomBroadcast);
        mBtsendCustomBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent("customName"); //广播名字
                //android8后的加上  pkq是app名,cls是所在类路径    //广播位置
                intent.setComponent(new ComponentName("com.example.huhaichang.learn3",
                        "com.example.huhaichang.learn3.eight.MyBroadcastReceiver")) ;
                sendBroadcast(intent);
                //发送有序广播
               //sendOrderedBroadcast(intent,null);

            }
        });
    }
}
