package com.example.huhaichang.learn3.twelve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class ServiceTestActivity extends AppCompatActivity {
    private Button mBtStartService,mBtStopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        mBtStartService = findViewById(R.id.bt_startService);
        mBtStopService = findViewById(R.id.bt_stopService);
        mBtStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceTestActivity.this,MyService.class);
                startService(intent);
            }
        });
        mBtStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceTestActivity.this,MyService.class);
                stopService(intent);
            }
        });
    }
}
