package com.example.huhaichang.learn3.fifteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.eleven.APP;
import com.example.huhaichang.learn3.eleven.widget.OkHttpUtil;

import com.example.huhaichang.learn3.fifteen.widget.JsonUtil;
import com.example.huhaichang.learn3.fifteen.widget.UserId;
import com.example.huhaichang.learn3.widget.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginDataActivity extends AppCompatActivity {
    private Button mBtGetData;
    private TextView mTVGetData;
    private int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_data);
        mBtGetData = findViewById(R.id.bt_getLoginData);
        mTVGetData = findViewById(R.id.tv_getLoginData);
        mBtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://120.24.241.25:8080/api/home/useridlist/";
                OkHttpUtil.sendHttpRequest(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        ToastUtil.showMsg(LoginDataActivity.this,"获取失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String content =response.body().string();
                        String useData = JsonUtil.handleWeatherResponse(content);
                        parseJSONWithGSON(useData);
                    }
                });
            }
        });
    }
    private void parseJSONWithGSON(String jsonData) {
        String ba="";
        Gson gson = new Gson();
        List<UserId> userIdList = gson.fromJson(jsonData,new TypeToken<List<UserId>>(){}.getType());
        for (final UserId userId :userIdList){
            final String user = userId.getUser();
            final String password = userId.getPassword();
            ba = ba+"账号:"+user+"  密码:"+password+"\n";
            final String finalBa = ba;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTVGetData.setText(finalBa);
                }
            });
        }
    }
    private void parseJSONWithJSONObjiect(final String jsonData) {
        String b="";
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);//每一组的对象
                final String user = jsonObject.getString("user");
                final String password = jsonObject.getString("password");
                b = b+"账号:"+user+"  密码:"+password+"\n";

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String finalB = b;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTVGetData.setText(finalB);
            }
        });
    }
}
