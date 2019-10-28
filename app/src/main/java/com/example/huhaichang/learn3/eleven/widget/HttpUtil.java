package com.example.huhaichang.learn3.eleven.widget;

import com.example.huhaichang.learn3.eleven.widget.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huhaichang on 2019/8/4.
 */

public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        //开启线程 防止调用此方法时被主线程阻塞
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader=null;
                URL url = null;
                try {
                    url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//get是获取用输入流 POST是发送用输出流
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    //connection.getInputStream()用输入流
                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    //方法1
                    final StringBuilder respone = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        respone.append(line);
                    }
                    //数据给接口回调
                    if(listener != null){
                        listener.onFinish(respone.toString());
                    }
                } catch (Exception e) {
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }


            }
        }).start();
    }
}
