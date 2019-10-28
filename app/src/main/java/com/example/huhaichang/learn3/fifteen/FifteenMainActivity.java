package com.example.huhaichang.learn3.fifteen;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.huhaichang.learn3.MainActivity;
import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.fifteen.AD.ADActivity;
import com.example.huhaichang.learn3.thirteen.LBSTestActivity;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class FifteenMainActivity extends AppCompatActivity {
    private Button mBtIntent;
    private Button mBtBack;
    private Button mBtViewPaper;
    private Button mBtHYa,mBtHYa2;
    private Button mBtLoginData;
    private Button mBtRegisterData;
    private Button mBtYuan;
    private Button mBtSms;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private LocationClient mLocationClient;  //Gps定位
    private String city;
    private TextView mTvCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("11","222");
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_fifteen_main);
        mBtIntent = findViewById(R.id.bt_intent);
        mBtBack = findViewById(R.id.bt_99);
        mBtViewPaper = findViewById(R.id.bt_viewPaper);
        mBtHYa = findViewById(R.id.bt_hYa);
        mBtHYa2 = findViewById(R.id.bt_hYa2);
        mBtLoginData = findViewById(R.id.bt_loginData);
        mBtRegisterData=findViewById(R.id.bt_registerData);
        mBtYuan = findViewById(R.id.bt_yuan);
        mBtSms = findViewById(R.id.bt_sms);
        mTvCity = findViewById(R.id.tv_city);
        mSharedPreferences =getSharedPreferences("adPoint",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mBtIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this,Intent1Activity.class);
                startActivity(intent);
            }
        });
        mBtViewPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this,ViewPaperActivity.class);
                startActivity(intent);
            }
        });
        mBtHYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this,HYaActivity.class);
                startActivity(intent);
            }
        });
        mBtHYa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this,HYa2Activity.class);
                startActivity(intent);
            }
        });
        mBtLoginData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this,LoginDataActivity.class);
                startActivity(intent);
            }
        });
        mBtRegisterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this,RegisterDataActivity.class);
                startActivity(intent);
            }
        });
        mBtSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FifteenMainActivity.this,SMSActivity.class);
                startActivity(intent);
            }
        });
        mBtYuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSharedPreferences.getString("0","").equals("")){
                    mEditor.putString("0",city);
                mEditor.putString("1","图片");
                mEditor.putString("2","视频");
                mEditor.putString("3","音乐");
                mEditor.putString("4","游戏");
                mEditor.apply();
                }
                if(city==null){
                mEditor.putString("0","定位失败");
                }else {
                    mEditor.putString("0",city);
                }
                mEditor.apply();
                Intent intent = new Intent(FifteenMainActivity.this,ADActivity.class);
                startActivity(intent);
              /*  if(city==null){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(FifteenMainActivity.this,FifteenMainActivity.class);
                            startActivity(intent);
                        }
                    }, 1000);
                }else {
                    mEditor.putString("0",city);
                    mEditor.apply();
                    Intent intent = new Intent(FifteenMainActivity.this,ADActivity.class);
                    startActivity(intent);
                }*/

            }
        });
        //申请3个权限 列表add 变数组一起
        List<String> list = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(FifteenMainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(FifteenMainActivity.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(FifteenMainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //列表空就不用申请了 当需要申请时
        if(!list.isEmpty()){
            String as[]=list.toArray(new String[list.size()]);
            /**需要用数组*/
            ActivityCompat.requestPermissions(FifteenMainActivity.this,as,2);//然后得跳转到选择
        }else {
            requestLocation();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 2:       // 硬性条件                    只有申请的一中权限 3中的话grantResults[0]grantResults[1]grantResults[2]
                //原来的   if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if(grantResults.length>0){
                    //3种都判断 只要存在一个不行就不行  通过int[] grantResults
                    for(int m : grantResults){
                        if(m!=PackageManager.PERMISSION_GRANTED){
                            ToastUtil.showMsg(FifteenMainActivity.this,"必须同意所有权限");
                            finish();
                            return;
                        }
                        //直接获取
                        requestLocation();
                    }
                }else{   //拒绝
                    ToastUtil.showMsg(FifteenMainActivity.this,"发生未知错误");
                    finish();
                }
                break;
            default:
                break;
        }
    }
    private void requestLocation(){
        //直接获取
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);//要记得关闭
        mLocationClient.start();
    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            city=bdLocation.getDistrict();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvCity.setText(bdLocation.getDistrict());
                }
            });
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
