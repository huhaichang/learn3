package com.example.huhaichang.learn3.five;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.huhaichang.learn3.R;


public class FiveMainActivity extends AppCompatActivity {
    private Button mbtEvent,mbtHandler,mbtThradDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_five);
        mbtEvent =findViewById(R.id.bt_event);
        mbtHandler = findViewById(R.id.bt_handler);
        mbtThradDelay = findViewById(R.id.bt_threadDelay);
        mbtEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiveMainActivity.this,EventActivity.class);
                startActivity(intent);
            }
        });
        mbtHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiveMainActivity.this,HandlerActivity.class);
                startActivity(intent);
            }
        });
        mbtThradDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiveMainActivity.this,ThreadDelayActivity.class);
                startActivity(intent);
            }
        });
        Log.i("1",getTaskId()+"");
    }


}
