package com.example.huhaichang.learn3.sixteen;

import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

public class TweenActivity extends AppCompatActivity {
    private ImageView mIVFly;
    private float nowX,nowY;  //蝴蝶现在位置
    private float nextX,nextY;  //变换后的位置
    private float screenWidth;    //屏幕宽度
    DisplayMetrics metrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        mIVFly = findViewById(R.id.iv_fly);
        //获取屏幕宽1
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        screenWidth=p.x;
        //获取屏幕宽2
       /* WindowManager windowManager =getWindowManager();
        Display display =windowManager.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenWidth=metrics.widthPixels;*/

        //Animation与ImageView绑定
        final AnimationDrawable anim = (AnimationDrawable) mIVFly.getBackground();
        mIVFly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.start();
             /*   new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                asd();
                            }
                        });
                    }
                }, 0, 200);*/
            }
        });
    }
    // mIVFly.getX()默认为0,所以不用设置初始化nowX   android:layout_marginLeft="20dp"即便这样也是0
    private void asd(){
        //X轴移动
        if(nextX>screenWidth){  //让飞行距离为一个屏幕的宽度
            nextX=0;
            nowX = nextX;
        }else{
            nextX +=20;
        }
        //Y轴上下浮动
        nextY = (float) (nowY +(Math.random()*10-5));
        TranslateAnimation anim1 = new TranslateAnimation(nowX,nextX,nowY,nextY);
        nowX = nextX;   //为下次新anim1做准备
        nowY = nextY;
        anim1.setDuration(200);
        mIVFly.startAnimation(anim1);
    }
}
