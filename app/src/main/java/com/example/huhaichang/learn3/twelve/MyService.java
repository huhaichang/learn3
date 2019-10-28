package com.example.huhaichang.learn3.twelve;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.huhaichang.learn3.R;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private DownloadBinder mBinder =new DownloadBinder();
    private AlarmManager manager;
    private PendingIntent pi;

    class DownloadBinder extends Binder{
        private Timer timer = new Timer();
        private int a =0;
    public void startDownload(){
        Log.d("APP","startDownload");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(a<100) {
                    a++;
                }
            }
        }, 0, 1000);
    }
    public int getProgress(){
        Log.d("APP","getProgress");
        return a;
    }

}

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       // throw new UnsupportedOperationException("Not yet implemented");
        //搞个返回值DownloadBinder的对象
        return mBinder;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //android8.0后必须为通知分类 多2个步骤
        //步骤1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Service";
            String channelName = "服务消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
        }


        Log.d("Service","onCreate");
        Intent intent = new Intent(MyService.this,TwelveMainActivity.class);
        //为服务的通知添加点击事件跳转到
        PendingIntent pi =PendingIntent.getActivity(MyService.this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(MyService.this,"Service").
                setContentTitle("this is Service Title").
                setContentText("this is Service content").
                setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi).//为弹窗设置跳转
                build();
        startForeground(1,notification);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Service","服务解绑");
        return super.onUnbind(intent);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service","服务启动");
        //开启服务后让它每隔10s执行一次
         manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long a = SystemClock.elapsedRealtime()+10*1000; //测试用10s
        Intent intent1 = new Intent(MyService.this,MyService.class); //自跳转
         pi =PendingIntent.getService(MyService.this,0,intent1,0);
      /* if (Build.VERSION.SDK_INT >= 23) {
            manager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,a,pi); //很准时
        }
       else{
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,a,pi);
        }*/
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,a,pi);  //对时间要求不那么精准
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service","服务销毁");
        manager.cancel(pi);

    }
    //步骤2
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}
