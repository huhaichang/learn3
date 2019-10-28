package com.example.huhaichang.learn3.four;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

public class NewsContentActivity extends AppCompatActivity {
    TextView mtvTitle;
    private Fragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        mtvTitle = findViewById(R.id.tv_newsTitle);
        Bundle bundle = getIntent().getExtras();
        String contentTitle = bundle.getString("title");
        String content = bundle.getString("content");
        mtvTitle.setText("News: "+contentTitle);
        contentFragment = NewsContentFragment.repalceContent("Content: "+content);
        getFragmentManager().beginTransaction().add(R.id.fl_content,contentFragment).commitAllowingStateLoss();
    }
}
