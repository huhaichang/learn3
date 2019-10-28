package com.example.huhaichang.learn3.eleven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.eleven.widget.OkHttpUtil;
import com.example.huhaichang.learn3.widget.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONObjectFromJSONActivity extends AppCompatActivity {
    private Button mBtJSONObject;
    private TextView mTV987;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonobject_from_json);
        mBtJSONObject = findViewById(R.id.bt_jSONObjectJieXi);
        mTV987 = findViewById(R.id.tv_987);
        mBtJSONObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpUtil.sendHttpRequest("http://10.0.2.2/ty.json", new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String responseData = response.body().string();
                        parseJSONWithJSONObject(responseData);
                    }
                });
            }
        });
    }
    public void parseJSONWithJSONObject(final String jsonData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(jsonData);
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //每个节点的键获取值
                        String cityCode = jsonObject.getString("id");
                        String name = jsonObject.getString("HeWeather5");
                        Log.d("id:"+cityCode," HeWeather5:"+name);
                        mTV987.setText("id:"+cityCode+" HeWeather5:"+name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
