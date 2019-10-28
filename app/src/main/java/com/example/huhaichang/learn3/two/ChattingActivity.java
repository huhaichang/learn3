package com.example.huhaichang.learn3.two;

import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.two.recyclerview.Msg;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChattingActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputtext;
    private Button send;
    private RecyclerView msgRecyclerView;
    private Timer timer = new Timer();
    private ClientDemo client;
    private String a="";
    private String content;
    private String ly="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        initMsgs();
        inputtext = findViewById(R.id.et_message);
        send = findViewById(R.id.bt_send);
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        //列表Recycle
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        //绑定adapter
        final ChattingAdapter adapter =new ChattingAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                client = new ClientDemo();
                client.connect("172.21.2.249", 8177);

            }
        }).start();

       timer.schedule(new TimerTask() {
           @Override
            public void run() {

               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                           try
                           {
                               a = client.getmsg();  //获取不了消息
                           }catch(Exception e){
                           e.printStackTrace();
                           }
                         //问题待解决 一打开输入流读取到服务器消息 客户端接着就断开

                       Handler handler = new Handler();
                       handler.postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               if (!ly.equals(a)) {
                                   Msg msg1 = new Msg(a, Msg.TYPE_RECEIVED);
                                   msgList.add(msg1);
                                   adapter.notifyItemInserted(msgList.size() - 1);
                                   msgRecyclerView.scrollToPosition(msgList.size() - 1);
                                   ly=a;
                               }
                           }
                       }, 500);
                   }
               });

        }

       }, 1000, 1000);

        //设置点击事件了
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = inputtext.getText().toString();
                //当编辑框不为空时 给List加入元素
                if(!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    //数据刷新 否则每次发送后都会覆盖前面的发送  试了下可有可无
                    adapter.notifyItemInserted(msgList.size() - 1);
                    // adapter.notifyDataSetChanged();
                    //将recycleview定位到最后一行  一定要
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputtext.setText("");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            client.SendMsg(content);
                          //  a = client.getmsg();         //执行一次后就断开
                        }
                    }).start();
                }
            }
        });
        }


    private void initMsgs(){
        Msg msg1 = new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.closeClient();
        timer.cancel();

    }
}
