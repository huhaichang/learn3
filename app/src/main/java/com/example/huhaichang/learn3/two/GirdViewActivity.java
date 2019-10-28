package com.example.huhaichang.learn3.two;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.huhaichang.learn3.R;

/**
 * Created by huhaichang on 2019/5/24.
 */

public class GirdViewActivity extends AppCompatActivity {
    private GridView gv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        gv = findViewById(R.id.gv);
        gv.setAdapter(new MyAdapterActivity(GirdViewActivity.this));
    }
}
