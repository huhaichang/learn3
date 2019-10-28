package com.example.huhaichang.learn3.three;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class ProgressActivity extends AppCompatActivity {
   private ProgressBar mpb4;
   private Button mbtStart;
   private  Button mprogressdialog1,mprogressdialog2;
   private ProgressDialog progressDialog,progressDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
       mpb4 =findViewById(R.id.pb_4);
       mbtStart =findViewById(R.id.bt_process_1);
       mprogressdialog1 = findViewById(R.id.bt_process_2);
       mprogressdialog2 = findViewById(R.id.bt_process_3);
        OnClick onClick =new OnClick();
        mpb4.setOnClickListener(onClick);
        mbtStart.setOnClickListener(onClick);
        mprogressdialog1.setOnClickListener(onClick);
        mprogressdialog2.setOnClickListener(onClick);
        mpb4.setProgress(0);
    }
 Handler handler= new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         if(mpb4.getProgress()<=100){
             handler.postDelayed(runnable,100);
         }else
         {
             ToastUtil.showMsg(ProgressActivity.this,"加载完成");
         }

     }
 };



    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            mpb4.setProgress(mpb4.getProgress()+1);
            //发送空消息进入handler的handleMessage
            handler.sendEmptyMessage(0);
        }
    };
    Handler handler1= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(progressDialog1.getProgress()<100){
                handler.postDelayed(runnable1,1000);
            }else
            {
                ToastUtil.showMsg(ProgressActivity.this,"加载完成");
            }

        }
    };
    Runnable runnable1 =new Runnable() {
        @Override
        public void run() {
           progressDialog1.setProgress(progressDialog1.getProgress()+1);
            //发送空消息进入handler的handleMessage
            handler1.sendEmptyMessage(0);
        }
    };

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_process_1:
                    handler.sendEmptyMessage(0);
                    break;
                case R.id.bt_process_2:
                    progressDialog=new ProgressDialog(ProgressActivity.this);
                   progressDialog.setTitle("提示");
                    progressDialog.setMessage("正在加载");
                    progressDialog.setCancelable(false);
                    progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.dismiss();
                        }
                    });
                    progressDialog.show();
                break;
                case R.id.bt_process_3:
                    progressDialog1=new ProgressDialog(ProgressActivity.this);
                    progressDialog1.setTitle("提示");
                    progressDialog1.setMessage("正在加载");
                    progressDialog1.setCancelable(false);
                    progressDialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                    progressDialog1.setButton(DialogInterface.BUTTON_POSITIVE, "好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog1.dismiss();
                        }
                    });
                    progressDialog1.show();
                    break;

            }
        }
    }

}
