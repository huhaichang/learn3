package com.example.huhaichang.learn3.twelve;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceBindedActivity extends AppCompatActivity {
    private Button mBtBindService,mBtUnbindService,mBtGetProgress;
    private MyService.DownloadBinder downloadBinder;
    private TextView tv1;
    private Timer timer =new Timer();
    private String b ="";
    private boolean stop = false,asda=false;
    private ProgressDialog progressDialog1;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定后获取对象并开始下载 获取进度
            stop = false;
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
           timer.schedule(new TimerTask() {
               @Override
               public void run() {
                   if (!stop) {
                       b = "" + downloadBinder.getProgress();
                       if(b.equals(""+99)){
                           stop =true;
                       }
                   }else {
                       b="下载完成";
                   }
               }
           }, 0, 1000);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //解绑后

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_binded);
        mBtBindService = findViewById(R.id.bt_bindService);
        mBtUnbindService = findViewById(R.id.bt_unbindService);
        mBtGetProgress = findViewById(R.id.bt_getProgress);
        tv1 =findViewById(R.id.tv_1);
        //绑定服务会自己执行onCreate不用去开启startService 不会执行onStartCommand
        mBtBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceBindedActivity.this,MyService.class);
                //asda为解除绑定做准备
                asda= bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });
        progressDialog1 = new ProgressDialog(ServiceBindedActivity.this);
        mBtGetProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asda){
                    progressDialog1.setTitle("提示");
                    progressDialog1.setMessage("\n正在下载");
                    progressDialog1.setCancelable(false);
                    progressDialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog1.setButton(DialogInterface.BUTTON_POSITIVE, "好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog1.dismiss();
                        }
                    });
                    progressDialog1.show();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                   if(!stop){
                                       tv1.setText("当前进度为："+b);
                                   }else {
                                       tv1.setText("当前进度为：下载失败" );
                                   }
                                    int w ;
                                    if(!b.equals("下载完成")){
                                        w = Integer.parseInt(b);
                                    }else {
                                        w = 100;
                                    }
                                    progressDialog1.setProgress(w);
                                    if(progressDialog1.getProgress()<100){
                                        progressDialog1.setProgress(progressDialog1.getProgress()+1);
                                    }else
                                    {
                                        ToastUtil.showMsg(ServiceBindedActivity.this,"下载完成");
                                    }

                                }
                            });
                        }
                    },0,1000);
                }else{
                    ToastUtil.showMsg(ServiceBindedActivity.this,"服务未绑定");
                }

            }
        });
        mBtUnbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asda) {
                    unbindService(connection);
                    asda = false;
                    stop = true;
                }
            }
        });
    }
}
