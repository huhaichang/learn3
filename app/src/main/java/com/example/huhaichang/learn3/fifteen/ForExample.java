package com.example.huhaichang.learn3.fifteen;

/**
 * Created by huhaichang on 2019/8/20.
 */

public class ForExample {

    /**
     *
     1.* // 获取屏幕宽度
     DisplayMetrics dm = new DisplayMetrics();
     getWindowManager().getDefaultDisplay().getMetrics(dm);
     int screenW = dm.widthPixels;

     //设置动画图片偏移量
     private int offset = 0;==不偏移
     private int position_one;==获取的屏幕大小的1/3
     private int position_two;==获取的屏幕大小的2/3



     //执行偏移代码
     Animation animation = null ;
     //例：图片从偏移2个位置到偏移1个位置 即3个页面的页面3到页面2滑动
     animation = new TranslateAnimation(position_two, position_one, 0, 0);

      //应用到mImageView
     animation.setFillAfter(true);// true:图片停在动画结束位置
     animation.setDuration(300);
     mImageView.startAnimation(animation);
     */


    /**
    2. * ViewPaper(可以套多个fragment有滑动效果)
             fragmentManager = getSupportFragmentManager();
             mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList)); //弄一个fragmentList

             //让ViewPager缓存2个页面
             mViewPager.setOffscreenPageLimit(2);

             //设置滑动监听
             mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());


     */
}
