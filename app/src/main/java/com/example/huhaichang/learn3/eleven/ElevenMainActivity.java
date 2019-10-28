package com.example.huhaichang.learn3.eleven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class ElevenMainActivity extends AppCompatActivity {
    private Button mBtSendRequest;
    private Button okHttpSendRequest;
    private Button mBtPull,mBtSAX;
    private Button mBtJSONO,mBtGSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleven_main);
            mBtSendRequest = findViewById(R.id.bt_sendRequest);
            okHttpSendRequest = findViewById(R.id.bt_okHttpSendRequest);
            mBtPull = findViewById(R.id.bt_pull);
            mBtSAX = findViewById(R.id.bt_sAX);
            mBtJSONO = findViewById(R.id.bt_jSONObject);
            mBtGSON =findViewById(R.id.bt_gSON);
            mBtSendRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ElevenMainActivity.this,HttpURLConnectionActivity.class);
                    startActivity(intent);
                }
            });
            okHttpSendRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ElevenMainActivity.this,OkHttpActivity.class);
                    startActivity(intent);
                }
            });
            mBtPull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ElevenMainActivity.this,PullFromXMLActivity.class);
                    startActivity(intent);
                }
            });
            mBtSAX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ElevenMainActivity.this,SAXFromXMLActivity.class);
                    startActivity(intent);
                }
            });
            mBtJSONO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ElevenMainActivity.this,JSONObjectFromJSONActivity.class);
                    startActivity(intent);
                }
            });
            mBtGSON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ElevenMainActivity.this,GSONFromJSONActivity.class);
                    startActivity(intent);
                }
            });
    }
}
