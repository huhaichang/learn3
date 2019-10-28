package com.example.huhaichang.learn3.five;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class HandlerActivity extends AppCompatActivity {
    private Button mbtDelay,mbtThreadCmct;
    private Handler mHandler,mHandler1,mHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        Log.i("1.1",getTaskId()+"");
        mbtDelay = findViewById(R.id.bt_delay);
        mbtThreadCmct = findViewById(R.id.bt_threadCommunication);
        mbtDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ToastUtil.showMsg(HandlerActivity.this,"3s后跳转");
                mHandler = new Handler();
                mHandler1 = new Handler();
                mHandler2 = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showMsg(HandlerActivity.this,"2");
                        mHandler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showMsg(HandlerActivity.this,"1");
                                mHandler2.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showMsg(HandlerActivity.this,"跳转");
                                        Intent intent = new Intent(HandlerActivity.this,FiveMainActivity.class);
                                        startActivity(intent);
                                    }
                                }, 1000);
                            }
                        }, 1000);
                    }
                }, 1000);
            }
        });

        mbtThreadCmct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View v) {
                //主线程
                mHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what){
                            case 1:
                                ToastUtil.showMsg(HandlerActivity.this,"消息处理： 1号通信成功");
                                break;
                            case 2:
                                ToastUtil.showMsg(HandlerActivity.this,"消息处理： 2号通信成功");
                                break;
                        }

                    }
                };
                //外部线程
               /* new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                }.start();*/
               new Thread(new Runnable() {   //每次执行线程 就会跳转到主线程去执行相应代码
                   @Override
                   public void run() {
                       Message message = new Message();
                       //message.what = 1;
                       message.what = 2;
                       mHandler.sendMessage(message);
                   }
               }).start();
            }
        });


    }
}
