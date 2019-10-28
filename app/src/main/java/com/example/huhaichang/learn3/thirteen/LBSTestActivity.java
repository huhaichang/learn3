package com.example.huhaichang.learn3.thirteen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class LBSTestActivity extends AppCompatActivity {
    private TextView mTVPosition;
    private LocationClient mLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_lbstest);
        mTVPosition = findViewById(R.id.tv_position);
       /* 列表变数组
       List<String> list = new ArrayList<>();
        list.add("b");
        list.add("a");
        list.add("c");
       //mTVPosition.setText(list.get(0)+list.get(1)+list.get(2));
        String as[]=list.toArray(new String[list.size()]);
        mTVPosition.setText(as[0]+as[1]+as[2]);*/
       //申请3个权限 列表add 变数组一起
        List<String> list = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(LBSTestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(LBSTestActivity.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(LBSTestActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //列表空就不用申请了 当需要申请时
        if(!list.isEmpty()){
            String as[]=list.toArray(new String[list.size()]);
            /**需要用数组*/
            ActivityCompat.requestPermissions(LBSTestActivity.this,as,1);//然后得跳转到选择
        }else {
            requestLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:       // 硬性条件                    只有申请的一中权限 3中的话grantResults[0]grantResults[1]grantResults[2]
             //原来的   if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if(grantResults.length>0){
                 //3种都判断 只要存在一个不行就不行  通过int[] grantResults
                    for(int m : grantResults){
                        if(m!=PackageManager.PERMISSION_GRANTED){
                            ToastUtil.showMsg(LBSTestActivity.this,"必须同意所有权限");
                            finish();
                            return;
                        }
                        //直接获取
                        requestLocation();
                    }
                }else{   //拒绝
                    ToastUtil.showMsg(LBSTestActivity.this,"发生未知错误");
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private void requestLocation(){
        //直接获取
        initLocation(); //每5s获取一次实时位置
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        //强制使用GPS
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        //获取具体的位置 地点
        option.setIsNeedAddress(true);

        mLocationClient.setLocOption(option);//要记得关闭
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            //得到想要的bdLocation数据 设置ui
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder totalPositon = new StringBuilder();
                    totalPositon.append("伟度：").append(bdLocation.getLatitude()).append("\n");
                    totalPositon.append("经度：").append(bdLocation.getLongitude()).append("\n");
                    totalPositon.append("国家：").append(bdLocation.getCountry()).append("\n");
                    totalPositon.append("省：").append(bdLocation.getProvince()).append("\n");
                    totalPositon.append("市：").append(bdLocation.getCity()).append("\n");
                    totalPositon.append("区：").append(bdLocation.getDistrict()).append("\n");
                    totalPositon.append("街道：").append(bdLocation.getStreet()).append("\n");
                    totalPositon.append("城市id：").append("没有这东西").append("\n");
                   // totalPositon.append(bdLocation.getTime()).append("\n");
                    totalPositon.append("定位方式：");
                    if(bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                        totalPositon.append("GPS");
                    }else if(bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                        totalPositon.append("网络");
                    }
                    mTVPosition.setText(totalPositon);
                }
            });
        }
    }
}
