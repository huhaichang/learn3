package com.example.huhaichang.learn3.two;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.huhaichang.learn3.R;

public class ImageViewActivity extends AppCompatActivity {
   ImageView iv,iv_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //setContentView(R.layout.layout_title);
        setContentView(R.layout.activity_image_view);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.layout_title);
        iv = findViewById(R.id.iv_1);
        iv_2 =findViewById(R.id.iv_2);                                                   //https://www.baidu.com/img/dong1_a1c52951c1f40e1496b46b9ae415c121.gif
       Glide.with(this).load("http://b337.photo.store.qq.com/psb?/V13W7iWM2JLxaD/Rn9Aa.QJOB83Pebe5*JMh.JeFtUwEf.y8J5k2*NU9EY!/b/dNNN5MifDgAA&bo=gAKsAQAAAAABBw8!&rf=viewer_4").into(iv);//https://www.baidu.com/img/bd_logo1.png
       iv_2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ImageViewActivity.this,TwoMainActivity.class);
               startActivity(intent);
           }
       });
    }
}
