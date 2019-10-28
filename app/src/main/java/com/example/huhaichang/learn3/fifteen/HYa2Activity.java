package com.example.huhaichang.learn3.fifteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

public class HYa2Activity extends AppCompatActivity {
    private TextView mTVcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hya2);
        mTVcontent = findViewById(R.id.tv_content);



        StringBuilder sb =new StringBuilder();
        for(int i =0;i<500;i++){
            sb.append("fruitName ");
        }
        mTVcontent.setText(sb.toString());
    }
}
