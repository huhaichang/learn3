package com.example.huhaichang.learn3.ten;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.jar.Manifest;

public class PlayMediaActivity extends AppCompatActivity {
    private Button mBtPaly,mBtPause,mBtStop;
    private MediaPlayer mediaPlayer =new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_media);
        mBtPaly = findViewById(R.id.bt_play);
        mBtPause = findViewById(R.id.bt_pause);
        mBtStop = findViewById(R.id.bt_stop);
        //获取路径并准备工作
        if(ContextCompat.checkSelfPermission(PlayMediaActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(PlayMediaActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},5);
        }else {
            //有权限后就准备（初始化）
            initMediaPlayer();
        }
        //准备完毕开始操作
        mBtPaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
        });
        mBtPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
        mBtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                initMediaPlayer();
            }
        });
    }
    //准备代码
    private void initMediaPlayer() {
                           //内置SD卡下的音乐
        File file = new File(Environment.getExternalStorageDirectory(),"后来2018.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 5:
                //如果允许
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }
                else{
                    ToastUtil.showMsg(PlayMediaActivity.this,"你拒绝了这个权限");
                }
                break;
        }
    }
}
