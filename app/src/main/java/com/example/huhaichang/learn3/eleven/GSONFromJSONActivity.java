package com.example.huhaichang.learn3.eleven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.eleven.test.OkhttpUtil1;

import com.example.huhaichang.learn3.eleven.test.Utility;
import com.example.huhaichang.learn3.eleven.widget.OkHttpUtil;
import com.example.huhaichang.learn3.widget.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GSONFromJSONActivity extends AppCompatActivity {
    private Button mBtGSON;
    private Button mBtTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsonfrom_json);
        mBtGSON = findViewById(R.id.bt_gSONJieXi);
        mBtTest = findViewById(R.id.bt_test);
        mBtGSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://10.0.2.2/get_data.json")
                                .build();
                        try {
                            Response response =client.newCall(request).execute();
                             String responseData = response.body().string();
                            parseJSONWithGSON(responseData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        mBtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2/abcd.json";
                OkHttpUtil.sendHttpRequest(url, new okhttp3.Callback() {
                   @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                   @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseText = response.body().string();


                       try{
                            JSONObject jsonObject = new JSONObject(responseText);
                            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
                           String weatherContent = jsonArray.getJSONObject(0).toString();
                           Log.d("",weatherContent);


                       }catch (JSONException e){
                           e.printStackTrace();
                       }

                    }
                });



            }
        });
    }

    private void parseJSONWithGSON(final String jsonData){
        //把jsonData映射到类APP中
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showMsg(GSONFromJSONActivity.this,jsonData);
            }
        });
        Gson gson = new Gson();
        //用List 创建APP的N个实例对象
        List<APP> appList = gson.fromJson(jsonData,new TypeToken<List<APP>>(){}.getType());
       // APP app =gson.fromJson(jsonData,APP.class);//如果只有一组
        //基操
      for(APP app: appList){
            Log.d("id:"+app.getId(),"name:"+app.getName()+" version:"+app.getVersion());
        }
    }
}
