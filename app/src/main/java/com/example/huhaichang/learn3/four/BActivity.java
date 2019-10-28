package com.example.huhaichang.learn3.four;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

public class BActivity extends AppCompatActivity {
    private TextView mtv_user,mtv_password,mtv_email;
    private Button mbt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Log.d("BActivity","----onCreate----");
        Log.d("BActivity","taskID: "+getTaskId()+",  hash:"+hashCode());
        logtaskName();
        mtv_user = findViewById(R.id.tv_user);
        mtv_password = findViewById(R.id.tv_password);
        mtv_email = findViewById(R.id.tv_email);
        mbt_back = findViewById(R.id.bt_back);
        mbt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BActivity.this,AActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("user");
        String userTest = getIntent().getStringExtra("userTest");
        String email =bundle.getString("E-mail");
        String password = bundle.getString("password");
        //mtv_user.setText(user);
        //不用bundle传值
        mtv_user.setText(userTest);
        mtv_password.setText(password);
        mtv_email.setText(email);

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("BActivity","----onNewIntent----");
        Log.d("BActivity","taskID: "+getTaskId()+",  hash:"+hashCode());
        logtaskName();
    }

    private  void logtaskName(){
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            Log.d("AActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
