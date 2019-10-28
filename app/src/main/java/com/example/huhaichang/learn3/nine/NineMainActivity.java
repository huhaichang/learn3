package com.example.huhaichang.learn3.nine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class NineMainActivity extends AppCompatActivity {
    private Button mBtRuntime;
    private Button mBtGetPhone;
    private Button mBtCustomProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_main);
        mBtRuntime = findViewById(R.id.bt_runtimePermission);
        mBtGetPhone = findViewById(R.id.bt_getPhone);
        mBtCustomProvider = findViewById(R.id.bt_customProvider);
        mBtRuntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NineMainActivity.this,CallPhoneActivity.class);
                startActivity(intent);
            }
        });
        mBtGetPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NineMainActivity.this,PhoneInformationActivity.class);
                startActivity(intent);
            }
        });
        mBtCustomProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NineMainActivity.this,MyProviderActivity.class);
                startActivity(intent);
            }
        });
    }
}
