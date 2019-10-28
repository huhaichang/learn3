package com.example.huhaichang.learn3.eight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.three.CustomDialogActivity;

public class EightMainActivity extends AppCompatActivity {
    private Button mBtNetworkBCR;
    private Button mBtCustomBCR;
    private Button mBtLocalBCR;
    private Button mBt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_main);
        mBtNetworkBCR = findViewById(R.id.bt_networkBCR);
        mBtCustomBCR = findViewById(R.id.bt_customBCR);
        mBtLocalBCR = findViewById(R.id.bt_localBCR);
        mBt1 = findViewById(R.id.bt_1);
        mBtNetworkBCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EightMainActivity.this,NetworkBroadcastActivity.class);
                startActivity(intent);
            }
        });
        mBtCustomBCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EightMainActivity.this,CustomBroadcastActivity.class);
                startActivity(intent);
            }
        });
        mBtLocalBCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EightMainActivity.this,LocalCustomBroadcastActivity.class);
                startActivity(intent);
            }
        });
        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EightMainActivity.this,LogonActivity.class);
                startActivity(intent);
            }
        });
    }
}
