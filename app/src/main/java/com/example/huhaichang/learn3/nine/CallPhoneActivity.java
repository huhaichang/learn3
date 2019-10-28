package com.example.huhaichang.learn3.nine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class CallPhoneActivity extends AppCompatActivity {
    private EditText mETPhone;
    private Button mBtCallPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        mETPhone = findViewById(R.id.et_phone);
        mBtCallPhone = findViewById(R.id.bt_callPhone);
        //当有bundle传值时执行
        if(getIntent().getExtras()!=null) {
            Bundle bundle = getIntent().getExtras();
             String number = bundle.getString("number");
            mETPhone.setText(number);
        }
        mBtCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否授权 如果未授权申请授权在打电话 否则直接打电话
                if(ContextCompat.checkSelfPermission(CallPhoneActivity.this, Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED){
                    //申请授权
                    ActivityCompat.requestPermissions(CallPhoneActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else {
                    //有权限后就打电话
                    callPhone();
                }
            }
        });
    }

    private void callPhone(){
        if(!mETPhone.getText().toString().equals("")){
            try {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + mETPhone.getText().toString()));
                startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }else {
            ToastUtil.showMsg(CallPhoneActivity.this,"请输入电话号码");
        }
    }
//申请权限选择后调用
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                //如果允许
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    callPhone();
                }
                else{
                    ToastUtil.showMsg(CallPhoneActivity.this,"你拒绝了这个权限");
                }
                break;
                default:
        }
    }
}
