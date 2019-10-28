package com.example.huhaichang.learn3.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.huhaichang.learn3.R;

import java.util.jar.Attributes;

/**
 * Created by huhaichang on 2019/7/22.
 */
//封装一个自带标题的LinearLayout
 public class TitleLinearLayout extends LinearLayout{
    public TitleLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button back = findViewById(R.id.bt_99);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
