package com.example.huhaichang.learn3.eleven.widget;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huhaichang on 2019/8/4.
 */

public class OkHttpUtil {
    //okhttp已经存在接口类
    public static void sendHttpRequest(final String address, final okhttp3.Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(address)
                            .build();
                    client.newCall(request).enqueue(callback);
            }
        }).start();
    }
}
