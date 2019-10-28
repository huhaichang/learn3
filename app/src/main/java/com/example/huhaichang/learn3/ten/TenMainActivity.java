package com.example.huhaichang.learn3.ten;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class TenMainActivity extends AppCompatActivity {
    private Button mBtSendMsg;
    private Button mBtUseCamera;
    private Button mBtChoosePhoto;
    private Button mBtPlayMedia;
    private Button mBtPlayVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_main);
        mBtSendMsg = findViewById(R.id.bt_sendMsg);
        mBtUseCamera = findViewById(R.id.bt_useCamera);
        mBtChoosePhoto = findViewById(R.id.bt_choosePhoto);
        mBtPlayMedia =findViewById(R.id.bt_playMedia);
        mBtPlayVideo =findViewById(R.id.bt_playVideo);
        mBtSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TenMainActivity.this,SendNoticeActivity.class);
                startActivity(intent);
            }
        });
        mBtUseCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TenMainActivity.this,TakePhotoActivity.class);
                startActivity(intent);
            }
        });
        mBtChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TenMainActivity.this,TimeAddActivity.class);
                startActivity(intent);
            }
        });
        mBtPlayMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TenMainActivity.this,PlayMediaActivity.class);
                startActivity(intent);
            }
        });
        mBtPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TenMainActivity.this,PlayVideoActivity.class);
                startActivity(intent);
            }
        });
    }
}
