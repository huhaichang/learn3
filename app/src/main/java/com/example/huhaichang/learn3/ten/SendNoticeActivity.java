package com.example.huhaichang.learn3.ten;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.two.ChattingActivity;

import java.io.File;

public class SendNoticeActivity extends AppCompatActivity {
    private Button mBtSendNotice,mbtSendDingYue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notice);
        mBtSendNotice = findViewById(R.id.bt_sendNotice);
        mbtSendDingYue = findViewById(R.id.bt_senddingyue);
        //android8.0后必须为通知分类
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }

        mBtSendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//需要手动开启通知否则无效
                //如果被屏蔽点击跳转到设置里去开启
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = notificationManager.getNotificationChannel("chat");
                    if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                        startActivity(intent);
                        Toast.makeText(SendNoticeActivity.this, "通知已关闭请打开", Toast.LENGTH_SHORT).show();
                    }
                }

                //为聊天通知加入点击事件
                Intent intent =new Intent(SendNoticeActivity.this, ChattingActivity.class);
                PendingIntent pi = PendingIntent.getActivity(SendNoticeActivity.this,0,intent,0);
                Notification notification = new NotificationCompat.Builder(SendNoticeActivity.this,"chat")
                        .setContentTitle("这是聊天通知")
                        .setContentText("内容1235").
                        setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setAutoCancel(true)  //处理通知后让通知消失
                        .setContentIntent(pi) //为弹窗设置跳转
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        .setVibrate(new long[]{0,1000,5000,3000,2000,1000})  //设置声音震动 好像没什么用 都是默认的
                        .setLights(Color.GREEN,1000,1000)   //亲测颜色没用其它有用
                        .build();
                notificationManager.notify(1,notification);
            }
        });

        mbtSendDingYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent =new Intent(SendNoticeActivity.this, SendNoticeActivity.class);
                        PendingIntent pi = PendingIntent.getActivity(SendNoticeActivity.this,0,intent,0);
                        Notification notification = new NotificationCompat.Builder(SendNoticeActivity.this,"subscribe")
                                .setContentTitle("这是订阅通知")
                                //.setStyle(new NotificationCompat.BigTextStyle().bigText("您订阅的2222222222222222222222222222222222222222"))
                                .setContentText("您订阅的22222222222222222222222222222222222222222222")   //模拟器正常但是，当内容过多时上面的好像没用android9测试
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                .setAutoCancel(false)  //跳转回来后让通知不消失
                                .setDefaults(NotificationCompat.DEFAULT_ALL)  //声音震动默认
                                .setContentIntent(pi)   //点击跳转回来
                                //设置图片好像没用..
                                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.goodbay)))
                                .build();
                        notificationManager.notify(2,notification);
                    }
                }, 3000);

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

}
