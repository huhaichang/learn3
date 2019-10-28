package com.example.huhaichang.learn3.fourteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class CollapsingToolbarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView mIVTitle;
    private TextView mTVcontent,mTV9875;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTVcontent = findViewById(R.id.tv_content);
        mTV9875= findViewById(R.id.tv_9875);
        mIVTitle = findViewById(R.id.iv_title);
        mIVTitle.setImageResource(R.drawable.asdf);
        mIVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(CollapsingToolbarActivity.this,"图片");
            }
        });
        mTV9875.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(CollapsingToolbarActivity.this,"文字");
            }
        });
        StringBuilder sb =new StringBuilder();
        for(int i =0;i<500;i++){
            sb.append("fruitName ");
        }
        mTVcontent.setText(sb.toString());
    }
}
