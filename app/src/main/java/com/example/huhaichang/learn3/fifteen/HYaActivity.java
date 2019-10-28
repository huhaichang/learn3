package com.example.huhaichang.learn3.fifteen;

import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class HYaActivity extends AppCompatActivity {
    private TextView mTVcontent;
    private ScrollView mSV1;
    private ImageView mIVGetY;
    private View includeHide,include1;
    private LinearLayout mLlTest;
    private int[] location1 = new  int[2] ;
    private int a,b,startb=0;
    private boolean abc=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hya);
        mTVcontent = findViewById(R.id.tv_content);
        mSV1 = findViewById(R.id.sv_1);
        mIVGetY = findViewById(R.id.iv_getY);
        includeHide = findViewById(R.id.ic_hided);
        include1 = findViewById(R.id.ic_1);
        mLlTest = findViewById(R.id.ll_test);



        StringBuilder sb =new StringBuilder();
        for(int i =0;i<500;i++){
            sb.append("fruitName ");
        }

        mTVcontent.setText(sb.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSV1.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    //先获取第一次的b位置记为startb 减去图片长度c   就是隐藏所在的位置a
                    include1.getLocationOnScreen(location1);
                    b = location1[1];
                    if(abc){
                        startb=b;   //获取需置顶view初始位置
                        abc=false;
                    }

              //      int m = mIVGetY.getBottom();
                  a = startb - mIVGetY.getHeight(); //获取隐藏view的屏幕位置

             //   ToastUtil.showMsg(HYaActivity.this,""+a+"."+b+"."+startb+"."+m);
                   if(b>a) {
                        includeHide.setVisibility(View.GONE);
                       // includeHide.setVisibility(View.VISIBLE);
                    }else{
                        includeHide.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
