package com.example.huhaichang.learn3.eleven.test;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by huhaichang on 2019/8/16.
 */

public class OkhttpUtil1 {
    public static void sendHttpRequest(final String address1, final okhttp3.Callback callback){
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(address1)
                        .build();
                client.newCall(request).enqueue(callback);
    }
}
