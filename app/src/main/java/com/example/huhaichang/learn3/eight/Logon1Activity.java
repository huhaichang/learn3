package com.example.huhaichang.learn3.eight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.BaseActivity;

public class Logon1Activity extends BaseActivity {
    private Button mBt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon1);
        mBt1 = findViewById(R.id.bt_1);
        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Logon1Activity.this,Logon2Activity.class);
                startActivity(intent);
            }
        });
    }
}
