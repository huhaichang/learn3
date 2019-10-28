package com.example.huhaichang.learn3.eight;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.BaseActivity;

public class Logon2Activity extends BaseActivity {
    private Button mBtFinish;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon2);
        mBtFinish = findViewById(R.id.bt_finish);

        mBtFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("789");
                /*intent.setComponent(new ComponentName("com.example.huhaichang.learn3",
                        "com.example.huhaichang.learn3.eight.MyBroadcastReceiver")) ;*/
                sendBroadcast(intent);
            }
        });
    }
}
