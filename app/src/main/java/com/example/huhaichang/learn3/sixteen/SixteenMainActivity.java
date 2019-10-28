package com.example.huhaichang.learn3.sixteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

public class SixteenMainActivity extends AppCompatActivity {
    private Button mBtMove,mBtSearch,mBtPlane,mBtPlane2;
    private Button mBtTanqiu,mBtFrame,mBtTween;
    private TextView mTV5554;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixteen_main);
        mBtMove =findViewById(R.id.bt_move);
        mBtSearch = findViewById(R.id.bt_search);
        mBtPlane = findViewById(R.id.bt_plane);
        mBtPlane2 = findViewById(R.id.bt_plane2);
        mTV5554 = findViewById(R.id.tv_5554);
        mBtTanqiu = findViewById(R.id.bt_tanqiu);
        mBtFrame = findViewById(R.id.bt_frame);
        mBtTween = findViewById(R.id.bt_tween);
        mBtMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SixteenMainActivity.this,MoveActivity.class);
                startActivity(intent);
            }
        });
        mBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SixteenMainActivity.this,SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mBtPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SixteenMainActivity.this,PlaneActivity.class);
                startActivity(intent);
            }
        });
        mBtPlane2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SixteenMainActivity.this,Plane2Activity.class);
                startActivity(intent);
            }
        });
        mBtTanqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SixteenMainActivity.this,TanqiuActivity.class);
                startActivity(intent);
            }
        });
        mBtFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SixteenMainActivity.this,FrameActivity.class);
                startActivity(intent);
            }
        });
        mBtTween.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SixteenMainActivity.this,TweenActivity.class);
                startActivity(intent);
            }
        });
        String afc =getIntent().getStringExtra("context");
        if(afc!=null){
            mTV5554.setText("你选择了："+afc);
        }
    }

}
