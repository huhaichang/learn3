package com.example.huhaichang.learn3.widget;

import android.os.Build;
import android.util.Log;

/**
 * Created by huhaichang on 2019/8/14.
 */

public class LogUtil {
    public static final int VERBOSE=1;
    public static final int DEBUG=2;
    public static final int INFO=3;
    public static final int WARN=4;
    public static final int ERROR=5;
    public static final int NOTHING=6;       //不可更改

    public  static int level= VERBOSE;      //外界可更改就不用final了
    //让level等于多少我们就可以查看什么类型以上的日志
    //比如让level等于5 就只打印错误日志  等于6就不打印
    //为了方便记忆 我们给数字标记 如Error=5
    public static void v(String tag,String msg){
        if(level<=VERBOSE){
        Log.v(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if(level<=DEBUG){
            Log.d(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if(level<=INFO){
            Log.i(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if(level<=WARN){
            Log.w(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if(level<=ERROR){
            Log.e(tag,msg);
        }
    }

}
