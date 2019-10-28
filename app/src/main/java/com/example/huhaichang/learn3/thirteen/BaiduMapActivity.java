package com.example.huhaichang.learn3.thirteen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class BaiduMapActivity extends AppCompatActivity {
    private MapView mapView;
    private LocationClient mLocationClient;
    private BaiduMap baiduMap;
    private boolean a =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        mapView = findViewById(R.id.mv_baiDu);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true); //显示光标权限 记得要关闭
        //权限申请 先得到自己的位置 在显示自己的位置
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        List<String> list = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(BaiduMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(BaiduMapActivity.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(BaiduMapActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //列表空就不用申请了 当需要申请时
        if(!list.isEmpty()){
            String as[]=list.toArray(new String[list.size()]);
            ActivityCompat.requestPermissions(BaiduMapActivity.this,as,1);//然后得跳转到选择
        }else {
            requestLocation();
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
       // option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        //获取具体的位置 地点 现在只要经纬度暂时不用
    //    option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);//要记得关闭
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
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
                            ToastUtil.showMsg(BaiduMapActivity.this,"必须同意所有权限");
                            finish();
                            return;
                        }
                        //直接获取
                        requestLocation();
                    }
                }else{   //拒绝
                    ToastUtil.showMsg(BaiduMapActivity.this,"发生未知错误");
                    finish();
                }
                break;
            default:
                break;
        }
    }
    private void navigateTo(BDLocation bdLocation){
        //开始定位  利用baiduMap BaiduMap baiduMap = mapView.getMap();
        //我们只要只执行一次 以为它5s会执行一次
        if(a){
            LatLng ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude()); //经纬度汇总
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);  //定位
            baiduMap.animateMapStatus(update);//更新实现有效化
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);//更新实现有效化
            a=false;
        }
        //显示光标
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(bdLocation.getLatitude());
        locationBuilder.longitude(bdLocation.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            //得到想要的bdLocation数据 定位地图
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||
                    bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                //如果获取到位置把数据传出去
                navigateTo(bdLocation);
            }
        }
    }
}
