package com.example.huhaichang.learn3.fifteen.AD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.fifteen.FifteenMainActivity;
import com.example.huhaichang.learn3.fifteen.MFragmentPagerAdapter;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ADActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private LinearLayout mLLayout;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private List<View> pointList =new ArrayList<>();
    private FragmentManager fragmentManager;
    private Button mBt1475,mBtAdd;
    private SharedPreferences sharedPreferences;
    private String a,b,c,d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        mViewPager = findViewById(R.id.vp_details_top);
        mLLayout = findViewById(R.id.ll_details_top_dot);
        mBt1475 = findViewById(R.id.bt_1475);
        mBtAdd = findViewById(R.id.bt_add);
        sharedPreferences = getSharedPreferences("adPoint",MODE_PRIVATE);
        fragmentArrayList.add(new ADFragment(sharedPreferences.getString("0", "")));
        a=sharedPreferences.getString("1", "");
        b=sharedPreferences.getString("2", "");
        c=sharedPreferences.getString("3", "");
        d=sharedPreferences.getString("4", "");
        if(!sharedPreferences.getString("1","").equals("")) {
            fragmentArrayList.add(new ADFragment(a));
        }
        if(!sharedPreferences.getString("2","").equals("")) {
            fragmentArrayList.add(new ADFragment(b));
        }
        if(!sharedPreferences.getString("3","").equals("")) {
            fragmentArrayList.add(new ADFragment(c));
        }
        if(!sharedPreferences.getString("4","").equals("")) {
            fragmentArrayList.add(new ADFragment(d));
        }
        fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));
        for(int i =0;i<fragmentArrayList.size();i++){
            View point = new View(ADActivity.this);
            if(i==0){
            point.setBackgroundResource(R.drawable.yuandian);//view为自定义原点
                 }else{
                point.setBackgroundResource(R.drawable.yuandian1);
            }
            LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(18,18); //设置圆大小
            if(i>0){
            params.leftMargin=10;  //圆间隔
            }
            point.setLayoutParams(params);
            pointList.add(point);
            mLLayout.addView(point);
        }
        mViewPager.setOnPageChangeListener(new My1OnPageChangeListener());  //监听
        mBt1475.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //管理移除
                    Intent intent = new Intent(ADActivity.this,DeleteActivity.class);
                    startActivity(intent);


            }
        });
        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("")||b.equals("")||c.equals("")||d.equals("")){  //存在空
                    Intent intent = new Intent(ADActivity.this,AddActivity.class);
                    startActivity(intent);

                }else{
                    ToastUtil.showMsg(ADActivity.this,"最大上限为5");
                }
            }
        });
       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int s = fragmentArrayList.size();
                fragmentArrayList.clear();
                fragmentArrayList.add(new ADFragment(sharedPreferences.getString("0", "")));
                for(int i=1;i<s;i++){
                    fragmentArrayList.add(new ADFragment("文字"));
                }
                mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));
                asd();
                pointList.get(0).setBackgroundResource(R.drawable.yuandian);
                swipeRefreshLayout.setRefreshing(false);
            }
        });*/
    }
    //滑动监听
    public class My1OnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //数量小固定数量 就用这个(弄最大值为5个)
        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0://滑动到页面1
                    asd();
                    pointList.get(0).setBackgroundResource(R.drawable.yuandian);
                    break;
                case 1://滑动到页面2
                    asd();
                    pointList.get(1).setBackgroundResource(R.drawable.yuandian);
                    break;
                case 2://滑动到页面3
                    asd();
                    pointList.get(2).setBackgroundResource(R.drawable.yuandian);
                    break;
                case 3://滑动到页面4
                    asd();
                    pointList.get(3).setBackgroundResource(R.drawable.yuandian);
                    break;
                case 4://滑动到页面4
                    asd();
                    pointList.get(4).setBackgroundResource(R.drawable.yuandian);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private void asd(){
        for(int i =0;i<fragmentArrayList.size();i++) {
            pointList.get(i).setBackgroundResource(R.drawable.yuandian1);
        }
    }


}
