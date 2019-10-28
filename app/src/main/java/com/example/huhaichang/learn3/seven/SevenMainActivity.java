package com.example.huhaichang.learn3.seven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class SevenMainActivity extends AppCompatActivity {
    private Button mbtData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seven);
        mbtData = findViewById(R.id.bt_data);
        mbtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(SevenMainActivity.this, DataStorageActivity.class);
                startActivity(intent);
            }
        });
        Log.i("a","onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("a","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("a","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("a","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("a","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("a","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("a","onDestroy");
    }
}
