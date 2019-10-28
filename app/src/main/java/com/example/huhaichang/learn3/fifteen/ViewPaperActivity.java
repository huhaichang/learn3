package com.example.huhaichang.learn3.fifteen;


import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

import java.util.ArrayList;

public class ViewPaperActivity extends AppCompatActivity {
    private TextView mTvPicture,mTvMovie,mTvMusic;
    private ViewPager mViewPager;
    private ImageView mImageView;  //弄动画效果
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList=new ArrayList();

    //管理Fragment
    private FragmentManager fragmentManager;
    //当前页卡编号
    private int currIndex = 0;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;
    //动画图片宽度
    private int bmpW;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_view_paper);
        mTvPicture = findViewById(R.id.tv_picture);
        mTvMovie = findViewById(R.id.tv_movie);
        mTvMusic = findViewById(R.id.tv_music);
        mViewPager = findViewById(R.id.vp_1);
        mImageView = findViewById(R.id.cursor);
        fragmentArrayList.add(new Fragment1());
        fragmentArrayList.add(new Fragment2());
        fragmentArrayList.add(new Fragment3());
        fragmentManager = getSupportFragmentManager();
        InitViewPager(); //初始化ViewPager
        mTvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
        mTvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });
        mTvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);
            }
        });
     //动画效果添加
        //初始化ImageView
        InitImageView();

    }

    //初始化ViewPager
    private void InitViewPager() {
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList)); //弄一个fragmentList
        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);
        //设置默认打开第一页
        mViewPager.setCurrentItem(0);
        resetTextViewTextColor();  //全部初始化设为灰白
        mTvPicture.setTextColor(getResources().getColor(R.color.white));
        //设置滑动监听
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

    }
    //标题文字颜色初始化
    private void resetTextViewTextColor(){
        mTvPicture.setTextColor(getResources().getColor(R.color.huibai));
        mTvMusic.setTextColor(getResources().getColor(R.color.huibai));
        mTvMovie.setTextColor(getResources().getColor(R.color.huibai));
    }


    private void InitImageView() {
        // 获取分辨率宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;


        //动画图片偏移量赋值  移动1格2格
        position_one = (int) (screenW / 3.0);
        position_two = position_one * 2;

    }



    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation = null ;
            switch (position){
                case 0:                    //滑动到页面1
                    resetTextViewTextColor();
                    mTvPicture.setTextColor(getResources().getColor(R.color.white));

                    if(currIndex==1){                     //动画从页面1移动到页面0
                        animation = new TranslateAnimation(position_one, 0, 0, 0);

                    }else if(currIndex==2){ //动画从页面2移动到页面0
                        animation = new TranslateAnimation(position_two, 0, 0, 0);

                    }
                    break;
                case 1:                    //滑动到页面2
                    resetTextViewTextColor();
                    mTvMovie.setTextColor(getResources().getColor(R.color.white));
                    if(currIndex==0){
                        animation = new TranslateAnimation(0, position_one, 0, 0);

                    }else if(currIndex==2){
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    }
                    break;
                case 2:                    //滑动到页面3
                    resetTextViewTextColor();
                    mTvMusic.setTextColor(getResources().getColor(R.color.white));
                    if(currIndex==0){
                        animation = new TranslateAnimation(offset, position_two, 0, 0);

                    }else if(currIndex==1){
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                    }
                    break;
            }
            currIndex = position;


            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
            mImageView.startAnimation(animation);


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
