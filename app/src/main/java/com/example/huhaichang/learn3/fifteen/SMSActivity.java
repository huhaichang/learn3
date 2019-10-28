package com.example.huhaichang.learn3.fifteen;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

/**验证码还得与手机号码绑定  先不搞*/

public class SMSActivity extends AppCompatActivity {
    private Button mBtSend,mBtLogin,mbtOkSend;
    private EditText mETPhone,mETCode;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        mBtSend = findViewById(R.id.bt_send);
        mBtLogin = findViewById(R.id.bt_login);
        mETPhone = findViewById(R.id.et_phone);
        mETCode = findViewById(R.id.et_code);
        mbtOkSend = findViewById(R.id.bt_oksend);
        mSharedPreferences =getSharedPreferences("PasswordCode",MODE_PRIVATE);  //存放验证码
        mEditor = mSharedPreferences.edit();
        mBtSend.setVisibility(View.VISIBLE);
        mbtOkSend.setVisibility(View.GONE);
        if(!mSharedPreferences.getString("a","").equals("")){
            mBtSend.setVisibility(View.GONE);
            mbtOkSend.setVisibility(View.VISIBLE);
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(mSharedPreferences.getString("a","").equals("")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBtSend.setVisibility(View.VISIBLE);
                            mbtOkSend.setVisibility(View.GONE);
                        }
                    });
                    timer.cancel();
                }
            }
        }, 0, 1000);
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mETCode.getText().toString().equals("")||mETPhone.getText().toString().equals("")){
                    ToastUtil.showMsg(SMSActivity.this, "请输入验证码");
                }else {
                    if (mETCode.getText().toString().equals(mSharedPreferences.getString("a", ""))) {
                        ToastUtil.showMsg(SMSActivity.this, "登录成功");
                    } else {
                        ToastUtil.showMsg(SMSActivity.this, "登录失败");
                    }
                }
            }
        });
        requestPermissions(new String[]{Manifest.permission.SEND_SMS},0x123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==0x123&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            final SmsManager smsManager =SmsManager.getDefault();
            mBtSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if(mETPhone.getText().toString().equals("")){
                            ToastUtil.showMsg(SMSActivity.this,"请输入手机号码");
                        }else {
                            //5位随机码
                            String b = String.valueOf((int)((Math.random()*9+1)*10000));
                            mEditor.putString("a",b);
                            mEditor.apply();
                            //发送短信
                            mBtSend.setVisibility(View.GONE);
                            mbtOkSend.setVisibility(View.VISIBLE);
                            smsManager.sendTextMessage(mETPhone.getText().toString(),null,"您的QQ登录验证码为："+b,null,null);
                            ToastUtil.showMsg(SMSActivity.this,"发送成功60s内禁止再次发送");
                            //60S后改变数据
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mEditor.putString("a","");
                                    mEditor.apply();
                                    mBtSend.setVisibility(View.VISIBLE);
                                    mbtOkSend.setVisibility(View.GONE);

                                }
                            }, 60000);
                        }
                }
            });
        }else {
            ToastUtil.showMsg(SMSActivity.this,"您禁止了该权限");
        }
    }
}
