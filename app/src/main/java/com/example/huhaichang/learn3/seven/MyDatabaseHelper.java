package com.example.huhaichang.learn3.seven;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.huhaichang.learn3.widget.ToastUtil;

/**
 * Created by huhaichang on 2019/7/27.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    //创建名字为Book的表
    public static  final String CREATE_BOOK = "create table Book (" +
                                             "id integer primary key autoincrement,"+
                                             "author text,"+
                                             "price real,"+
                                             "pages integer,"
                                             +"name text)";
    //创建Category的表
    public static  final String CREATE_CATEGORY = "create table Category("+
                                                  "id integer primary key autoincrement,"+
                                                  "category_name text,"+
                                                  "category_code integer)";
    public static  final String CREATE_CATEGORYT = "create table CategoryT("+
            "id integer primary key autoincrement,"+
            "category_name text,"+
            "category_code integer)";
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext =context;
    }
//当版本更新 不会执行onCreate 在更新上删除原来的重新创建
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        db.execSQL(CREATE_CATEGORYT);
        //ToastUtil.showMsg(mContext,"SQL数据库创建成功");
    }
   //当版本号变大时调用更新方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //之前左右1个Book表  现在在加一个Category表就得更新
        //删除
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        db.execSQL("drop table if exists CategoryT");
        //重新创建
        onCreate(db);

    }
}
