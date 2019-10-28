package com.example.huhaichang.learn3.ten;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimeAddActivity extends AppCompatActivity {
    private Button mBtChoosePhoto;
    private TextView tv1;
    int a = 10;
    private Timer timer = new Timer();
 /*   private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                a++;
                tv1.setText(a +"");
            }
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
        mBtChoosePhoto = findViewById(R.id.bt_choosePhoto);
         tv1 = findViewById(R.id.tv_1);
        tv1.setText(a +"");

         mBtChoosePhoto.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 timer.schedule(new TimerTask() {
                     @Override
                     public void run() {
                         //2种方法
                   //     Message msg = new Message();
                 //      msg.what=1;
                //   handler.sendMessage(msg);
                         a++;
                         //此方法相当于上诉方法的封装
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 tv1.setText(a+"");
                             }
                         });


                     }
                 },0,1000);
                 }
         });
    }
}
