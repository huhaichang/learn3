package com.example.huhaichang.learn3.five;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.MyButton;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class EventActivity extends AppCompatActivity {
    private Button mbtclick;
    private MyButton mybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mbtclick = findViewById(R.id.bt_1);
        mybtn = findViewById(R.id.bt_back5);
        //优先级最高
        mybtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("mybtn", "---onTouchEventDown--- ");
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("mybtn", "---onTouchEventUp--- ");
                        break;
                }
                return false;

            }
        });
        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventActivity.this,FiveMainActivity.class);
                startActivity(intent);
            }
        });
        mbtclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(EventActivity.this, "click1....");
            }
        });
    }

    //对于同时执行的点击事件 以最后面写的为准,覆盖前面的点击事件
    //1.通过内部类，例如ThreeMainActivity
    //2.通过匿名内部类 自己经常用的就是
    //3.通过外部类去实现View.OnClickEvent
    //4.通过所在事件源去实现View.OnClickListener 如：EventActivity extends AppCompatActivity implements View.OnClickListener
    //5.布局内设置的点击事件 为最开始写的如下：
    public void show(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                ToastUtil.showMsg(EventActivity.this, "click....");
                break;
        }
    }

    //由于MyButton控件已经禁止外部调用时重写onTouchEvent所以下列代码无效
    //对于按键来说也相当于外部调用对应Activity代码里返回true则会屏蔽改按键

    //优先级第3
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("Activity", "---onTouchEventDown--- ");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("Activity", "---onTouchEventUp--- ");
                break;
        }
        return false;
    }

    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}

