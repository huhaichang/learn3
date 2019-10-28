package com.example.huhaichang.learn3.two;

import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.logging.Handler;
import java.util.logging.SocketHandler;

/**
 * Created by huhaichang on 2019/7/22.
 */

public class ClientDemo {
    String sb="";
    OutputStream out;
    InputStream in;
    Socket client;
    public  void connect(String ipAddress,int port){
        try {
            client = new Socket(ipAddress,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void SendMsg(String a) {

        try {
              out = client.getOutputStream();
            out.write(a.getBytes("UTF-8"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getmsg() {
       /* BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final BufferedReader finalBr = br;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sb = finalBr.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Looper.prepare();*/
        try {
            if(out!=null){
         //  client.shutdownOutput();  //执行这个客户端就断开了
                 }
            in = client.getInputStream();
            int len;
            byte data[]= new byte[128];
            while((len = in.read(data))!=-1) {
                sb = new String(data, 0, len, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }
    public void closeClient(){
        try {
            if(out!=null){
            out.close();
            }
            if(in!=null){
            in.close();
            }
           if(client!=null){
            client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
