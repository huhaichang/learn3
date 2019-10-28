package com.example.huhaichang.learn3.two;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.huhaichang.learn3.MainActivity;
import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.two.recyclerview.GridRecycleViewActivity;
import com.example.huhaichang.learn3.two.recyclerview.HoriRecycleViewActivity;
import com.example.huhaichang.learn3.two.recyclerview.LinearRecycleViewActivity;
import com.example.huhaichang.learn3.two.recyclerview.PubuRecyclerViewActivity;
import com.example.huhaichang.learn3.three.ThreeMainActivity;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class TwoMainActivity extends AppCompatActivity {
     private Button bt;
     private Button gv;
     private Button lrv;
     private Button spgd;
     private Button grv;
     private Button prv;
     private Button wv;
     private Button nc;
     private Button three;
     private Button back;
     private Button mBtRaidoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_main);
        //隐藏标题 或者在androidManifest设置
        /*ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }*/
        bt = findViewById(R.id.bt_1);
        gv = findViewById(R.id.bt_2);
        lrv = findViewById(R.id.bt_3);
        spgd = findViewById(R.id.bt_4);
        grv = findViewById(R.id.bt_5);
        prv = findViewById(R.id.bt_6);
        wv  = findViewById(R.id.bt_7);
        nc  = findViewById(R.id.bt_8);
        three = findViewById(R.id.bt_9);
        mBtRaidoButton = findViewById(R.id.bt_10);
        //直接调用title.xml文件
        back = findViewById(R.id.bt_99);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,ImageViewActivity.class);
                startActivity(intent);
            }
        });
        gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TwoMainActivity.this,GirdViewActivity.class);
                startActivity(intent);
            }
        });
        lrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,LinearRecycleViewActivity.class);
                startActivity(intent);
            }
        });
        spgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,HoriRecycleViewActivity.class);
                startActivity(intent);
            }
        });
        grv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,GridRecycleViewActivity.class);
                startActivity(intent);
            }
        });
        prv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,PubuRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        wv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,NewsActivity.class);
                startActivity(intent);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,ChattingActivity.class);
                startActivity(intent);
            }
        });
        mBtRaidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoMainActivity.this,RaidoButtonActivity.class);
                startActivity(intent);
            }
        });
    }
}
