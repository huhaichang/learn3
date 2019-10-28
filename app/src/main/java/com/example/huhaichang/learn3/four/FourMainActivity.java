package com.example.huhaichang.learn3.four;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class FourMainActivity extends AppCompatActivity {
    private Button mbtdateintent;
    private Button mbtfragment1;
    private Button mbtNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("2",getTaskId()+"");
        setContentView(R.layout.activity_four_main);
        mbtdateintent = findViewById(R.id.bt_1);
        mbtfragment1 = findViewById(R.id.bt_2);
        mbtNews = findViewById(R.id.bt_3);
        mbtdateintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourMainActivity.this,AActivity.class);
                startActivity(intent);
            }
        });
        mbtfragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourMainActivity.this,ContainerActivity.class);
                startActivity(intent);
            }
        });
        mbtNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourMainActivity.this,NewTitleActivity.class);
                startActivity(intent);
            }
        });
    }
}
