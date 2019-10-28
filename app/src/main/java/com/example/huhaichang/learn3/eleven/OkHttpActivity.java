package com.example.huhaichang.learn3.eleven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.eleven.widget.OkHttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


public class OkHttpActivity extends AppCompatActivity {
    private Button mBtSendRequest;
    private TextView mTVBackHTML;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        mBtSendRequest = findViewById(R.id.bt_sendRequest);
        mTVBackHTML = findViewById(R.id.tv_backHTML);
        mBtSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /*    OkHttpUtil.sendHttpRequest("http://10.0.2.2/abcd.json", new okhttp3.Callback() {
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String responseData = response.body().string();
                        Gson gson = new Gson();
                        APP app =gson.fromJson(responseData,APP.class);
                        Log.d("id:"+app.getId(),"name:"+app.getName()+" version:"+app.getName());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTVBackHTML.setText(responseData);
                            }
                        });

                    }
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }
                });*/
            OkHttpUtil.sendHttpRequest("http://10.0.2.2/abcd.json", new okhttp3.Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    APP app =gson.fromJson(responseData,APP.class);
                    Log.d("id:"+app.getId(),"name:"+app.getName()+" version:"+app.getName());
                }
            });
            }
        });

    }
}
