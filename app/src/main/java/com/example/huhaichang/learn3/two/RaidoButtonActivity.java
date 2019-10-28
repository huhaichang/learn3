package com.example.huhaichang.learn3.two;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class RaidoButtonActivity extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raido_button);
        rb1 = findViewById(R.id.rb_1);
        rb2 = findViewById(R.id.rb_2);
        rb3 = findViewById(R.id.rb_3);
        rg = findViewById(R.id.rg);
        initImage();
        rb1.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.goodbay1),null,null);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_1:
                        ToastUtil.showMsg(RaidoButtonActivity.this,"图片");
                        initImage();
                        rb1.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.goodbay1),null,null);
                        break;
                    case R.id.rb_2:
                        ToastUtil.showMsg(RaidoButtonActivity.this,"视频");
                        initImage();
                        rb2.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.goodbay1),null,null);
                        break;
                    case R.id.rb_3:
                        initImage();
                        ToastUtil.showMsg(RaidoButtonActivity.this,"音乐");
                        rb3.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.goodbay1),null,null);
                        break;
                }
            }
        });
    }
    private void initImage(){
        rb1.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.icon),null,null);
        rb2.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.icon),null,null);
        rb3.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.icon),null,null);
    }
}
