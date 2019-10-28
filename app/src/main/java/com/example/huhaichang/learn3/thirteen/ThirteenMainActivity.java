package com.example.huhaichang.learn3.thirteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class ThirteenMainActivity extends AppCompatActivity {
    private Button mBtLBSTest,mBtMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirteen_main);
    mBtLBSTest = findViewById(R.id.bt_lBSTest);
    mBtMap = findViewById(R.id.bt_map);
    mBtLBSTest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(ThirteenMainActivity.this,LBSTestActivity.class);
            startActivity(intent);
        }
    });
    mBtMap.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(ThirteenMainActivity.this,BaiduMapActivity.class);
            startActivity(intent);
        }
    });
    }
}
