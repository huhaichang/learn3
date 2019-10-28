package com.example.huhaichang.learn3.seven.datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.seven.MyDatabaseHelper;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class SQLiteActivity extends AppCompatActivity {
    private Button mBtCreateSQL;
    private Button mBtAddData;
    private Button mBtUpData;
    private Button mBtDeleteData;
    private Button mBtQueryData;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        mBtCreateSQL = findViewById(R.id.bt_createSQLite);
        mBtAddData = findViewById(R.id.bt_addData);
        mBtUpData = findViewById(R.id.bt_upData);
        mBtDeleteData = findViewById(R.id.bt_deleteData);
        mBtQueryData = findViewById(R.id.bt_queryData);
                                                                   //数据库名字      返回的     版本号1改变2在变为3
        myDatabaseHelper = new MyDatabaseHelper(this,"BookStore.db",null,10);


        mBtCreateSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
        mBtAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                //加入数据
                ContentValues contentValues = new ContentValues();

                contentValues.put("name","1号的书");
                contentValues.put("author","1号");
                contentValues.put("price","15.86");
                contentValues.put("pages","454");
                //数据放到表Book中
                db.insert("Book",null,contentValues);
                //清空数据
                contentValues.clear();
                //继续加入数据
                contentValues.put("name","2号的书");
                contentValues.put("author","2号");
                contentValues.put("price","19.76");
                contentValues.put("pages","654");
                //数据放到表Book中
                db.insert("Book",null,contentValues);
                ToastUtil.showMsg(SQLiteActivity.this,"添加成功");
            }
        });
        mBtUpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("price","215.86");
                //db.update("Book",contentValues,"name = ?",new String[]{"2号的书"}); //更新书名name="2号的书"的书所在的那一行
                db.update("Book",contentValues,"id = ?",new String[]{"2"});//更新id="2"的书所在的那一行
                ToastUtil.showMsg(SQLiteActivity.this,"更新id=2的数据");
            }
        });
        mBtDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= myDatabaseHelper.getWritableDatabase();
                db.delete("Book","id = ?",new String[]{"3"});
                ToastUtil.showMsg(SQLiteActivity.this,"删除id等于3的数据");
            }
        });
        mBtQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= myDatabaseHelper.getWritableDatabase();
                Cursor cursor= db.query("Book",null,null,null,null,null,null);
                //从第一个开始
                if(cursor.moveToFirst()){

                    //用do--while循环 到没用下一个时结束
                    do{
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Log.d("id: "+id,"书名:"+name+"  作者:"+author+" 价格:"+price+" 页数"+pages);
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
