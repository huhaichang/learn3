package com.example.huhaichang.learn3.fourteen;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class FloatingActionButtonActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton mFAButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFAButton = findViewById(R.id.fab);
        mFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不可交互提示
               // ToastUtil.showMsg(FloatingActionButtonActivity.this,"这是不可交互提示");
                //可交互提示                                                 设置按钮
                Snackbar.make(v,"这是可交互提示",Snackbar.LENGTH_SHORT).setAction("这是文字按钮", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(FloatingActionButtonActivity.this,"可交互提示的点击事件");
                    }
                }).show();
            }
        });
    }
}
