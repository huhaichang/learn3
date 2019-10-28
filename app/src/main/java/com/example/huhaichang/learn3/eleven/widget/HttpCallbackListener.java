package com.example.huhaichang.learn3.eleven.widget;

/**
 * Created by huhaichang on 2019/8/4.
 */
//用于接收回调的数据
public interface HttpCallbackListener {
    //response为服务器返回数据
    void onFinish(String response);
    //网络操作出现错误时调用
    void onError(Exception e);
}
