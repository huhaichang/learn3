package com.example.huhaichang.learn3.twelve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class StyleServiceActivity extends AppCompatActivity {
    private Button mBtBeforeService,mBtIntentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_service);
        mBtBeforeService = findViewById(R.id.bt_beforeService);
        mBtIntentService = findViewById(R.id.bt_intentService);
        mBtBeforeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StyleServiceActivity.this,TwelveMainActivity.class);
                startActivity(intent);
            }
        });
        mBtIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyIntentActivity","Thread id is"+Thread.currentThread().getId());
                Intent intent = new Intent(StyleServiceActivity.this,MyIntentService.class);
                startService(intent);
            }
        });
    }
}
