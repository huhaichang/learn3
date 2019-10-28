package com.example.huhaichang.learn3.sixteen;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.huhaichang.learn3.R;

public class FrameActivity extends AppCompatActivity {
    private Button mBtStart,mBStop;
    private ImageView mIVKongfu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        mBtStart = findViewById(R.id.bt_start);
        mBStop = findViewById(R.id.bt_stop);
        mIVKongfu = findViewById(R.id.iv_kongfu);
        //绑定 AnimationDrawable与ImageView（背景设置自定义xml文件）
        final AnimationDrawable anim = (AnimationDrawable) mIVKongfu.getBackground();
        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.start();
            }
        });
        mBStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.stop();

            }
        });
    }
}
