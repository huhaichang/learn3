package com.example.huhaichang.learn3.ten;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.io.File;

public class PlayVideoActivity extends AppCompatActivity {
    private VideoView mVVPlayVideo;
    private Button mBtPaly,mBtPause,mBtStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        mVVPlayVideo = findViewById(R.id.vv_playVideo);
        mBtPaly = findViewById(R.id.bt_play);
        mBtPause = findViewById(R.id.bt_pause);
        mBtStop = findViewById(R.id.bt_stop);
        //获取路径并准备工作
        if(ContextCompat.checkSelfPermission(PlayVideoActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(PlayVideoActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},6);
        }else {
            //有权限后就准备（初始化）
            initVideoPlayer();
        }
        mBtPaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mVVPlayVideo.isPlaying()){
                    mVVPlayVideo.start();
                }
            }
        });
        mBtPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mVVPlayVideo.isPlaying()){
                    mVVPlayVideo.pause();
                }
            }
        });
        mBtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mVVPlayVideo.isPlaying()){
                    mVVPlayVideo.resume();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mVVPlayVideo.pause();
                        }
                    }, 100);

                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mVVPlayVideo!=null){
            mVVPlayVideo.suspend();
        }
    }
   //弄好路径
    private void initVideoPlayer() {
        File file =new File(Environment.getExternalStorageDirectory(),"1234.mp4");//内置SD卡下的
        mVVPlayVideo.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 6:
                //如果允许
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initVideoPlayer();
                }
                else{
                    ToastUtil.showMsg(PlayVideoActivity.this,"你拒绝了这个权限");
                }
                break;
        }
    }

}
