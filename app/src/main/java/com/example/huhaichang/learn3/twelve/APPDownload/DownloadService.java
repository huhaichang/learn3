package com.example.huhaichang.learn3.twelve.APPDownload;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.io.File;

public class DownloadService extends Service {
    private DownloadBinder mBinder =new DownloadBinder();
    private String downloadUrl;
    private DownloadTask downloadTask;
    //重写接口
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
        getNotificationManager().notify(1,getNotification("Download...",progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            //当下载成功关闭前台服务通知 换个正常的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            ToastUtil.showMsg(DownloadService.this,"下载完成");
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            //当下载失败关闭前台服务通知 换个正常的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            ToastUtil.showMsg(DownloadService.this,"下载失败");
        }

        @Override
        public void onPaused() {
            downloadTask = null;
           // stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-2));
            ToastUtil.showMsg(DownloadService.this,"下载暂停");
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            ToastUtil.showMsg(DownloadService.this,"下载取消");
        }
    };

    class DownloadBinder extends Binder {

        public void startDownload(String url){
            //暂停下载后downloadTask还是等于null
        if(downloadTask == null){
            downloadUrl =url;
            downloadTask = new DownloadTask(listener); //构造函数把重写的接口放入DownloadTask
            downloadTask.execute(downloadUrl);
            startForeground(1,getNotification("Download...",0));
            ToastUtil.showMsg(DownloadService.this,"下载开始");
        }
        }
        public void pauseDownload(){
            //把设置DownloadTask的isPaused=true传入进去就行
            if(downloadTask!=null){
                downloadTask.pauseDownload();
            }

        }
        public void cancelDownload(){
            if(downloadTask!=null){
                downloadTask.cancelDownload();
            }
            //还得删除文件 关闭通知
            if(downloadUrl !=null){
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
               File file = new File(directory+fileName);
               if(file.exists()){
                   file.delete();
               }
               //取消id为1的通知
               getNotificationManager().cancel(1);
               stopForeground(true);
                ToastUtil.showMsg(DownloadService.this,"下载取消");
            }
        }


    }
    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Service";
            String channelName = "服务消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

        }

    }

    //加入服务通知 封装好
    // NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         //   notificationManager.notify(1,/*封装如下2行*/new NotificationCompat.Builder(DownloadService.this,"Service").build());
    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    private Notification getNotification(String title,int progress){
        Intent intent = new Intent(DownloadService.this,DownloadAPPlicationActivity.class);
        PendingIntent pi = PendingIntent.getActivity(DownloadService.this,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(DownloadService.this,"Service");
               builder.setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi);//为弹窗设置跳转
                //未下载或下载成功失败都设置为-1就不会显示进度
               if(progress>0){
                   builder.setContentText(progress+"%")
                           .setProgress(100,progress,false);
               }else if(progress==0){
                   builder.setContentText("准备中")
                           .setProgress(100,progress,false);
               }else if(progress==-2){
                   builder.setContentText("下载暂停")
                           .setProgress(100,progress,false);
               }
      return builder.build();

    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}
