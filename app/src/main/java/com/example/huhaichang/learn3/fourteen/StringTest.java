package com.example.huhaichang.learn3.fourteen;

/**
 * Created by huhaichang on 2019/8/13.
 */

public class StringTest {
    String a;
    int b=0;

    public StringTest(String a){
        this.a=a;
        b=11146;
    }
    public StringTest(String a,int b){
        this.a=a;
        this.b=b;
    }
    public String getName(){
       return a;
    }
    public int getId(){
        return b;
    }

}

