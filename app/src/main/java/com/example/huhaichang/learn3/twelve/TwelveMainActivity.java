package com.example.huhaichang.learn3.twelve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.twelve.APPDownload.DownloadAPPlicationActivity;

public class TwelveMainActivity extends AppCompatActivity {
    private Button mBtOpenService,mBtBindService;
    private Button mBtStyleService,mBtDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve_main);
        mBtOpenService = findViewById(R.id.bt_openService);
        mBtBindService = findViewById(R.id.bt_bindService);
        mBtStyleService = findViewById(R.id.bt_styleService);
        mBtDownload = findViewById(R.id.bt_downloadApplication);
        mBtOpenService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwelveMainActivity.this,ServiceTestActivity.class);
                startActivity(intent);
            }
        });
        mBtBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwelveMainActivity.this,ServiceBindedActivity.class);
                startActivity(intent);
            }
        });
        mBtStyleService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwelveMainActivity.this,StyleServiceActivity.class);
                startActivity(intent);
            }
        });
        mBtDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwelveMainActivity.this,DownloadAPPlicationActivity.class);
                startActivity(intent);
            }
        });
    }
}
