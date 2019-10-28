package com.example.huhaichang.learn3.two.recyclerview;

/**
 * Created by huhaichang on 2019/7/22.
 */

//用来表示发送的内容或接收的内容
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    private String content;
    private int type;
    public Msg(String content,int type){
        this.content = content;
        this.type =type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
