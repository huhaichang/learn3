package com.example.huhaichang.learn3.eleven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PullFromXMLActivity extends AppCompatActivity {
    private Button mBtPull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_applica);
        mBtPull = findViewById(R.id.bt_pullJieXi);
        mBtPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://10.0.2.2/get_data.xml")
                                .build();
                        try {
                            Response response =client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseXMLWithPull(responseData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
    private void parseXMLWithPull(String xmlData){
        //把返回的XML数据通过Pull解析
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser =factory.newPullParser();
            //xml数据放进去
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id ="";
            String name ="";
            String version ="";
            //每次循环读取一个节点(完成赋值并打印)
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    //开始解析(赋值)
                    case XmlPullParser.START_TAG: {
                        if("id".equals(nodeName)){
                            id = xmlPullParser.nextText();
                        }else if("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        }else if("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    //完成解析（打印）
                    case XmlPullParser.END_TAG: {
                        if("app".equals(nodeName)){
                            Log.d("id:"+id,"name:"+name+" version:"+version);
                        }
                        break;
                    }
                    default:
                        break;
                }
                //完成这个节点继续下一个
                    eventType = xmlPullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
