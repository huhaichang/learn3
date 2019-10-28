package com.example.huhaichang.learn3.eleven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionActivity extends AppCompatActivity {
    private Button mBtSendRequest;
    private TextView mTVBackHTML;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);
        mBtSendRequest = findViewById(R.id.bt_sendRequest);
        mTVBackHTML = findViewById(R.id.tv_backHTML);
        mBtSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送HTTP请求
                sendRequestWithHttpURLConnection();
            }
        });
    }
    //请求代码
    private void sendRequestWithHttpURLConnection(){
        //通过线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//get是获取用输入流 POST是发送用输出流
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //connection.getInputStream()用输入流
                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    //方法1
                    final StringBuilder respone = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null){
                        respone.append(line);
                    }
                    //方法2
               /*    int len;
                    byte buffer[] = new byte[1024];
                    String respone ="123";
                   while ((len=inputStream.read(buffer))!=-1){
                     respone = new String(buffer,0,buffer.length);
                   }
                  final String finalRespone = respone;*/

                    //由于现在处于子线程中
                    // 子线程不允许Ui操作，通过runOnUiThread使本应在子线程执行的变为主线程去执行
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //ui操作
                            mTVBackHTML.setText(respone.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
