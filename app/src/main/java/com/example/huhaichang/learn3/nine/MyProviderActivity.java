package com.example.huhaichang.learn3.nine;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class MyProviderActivity extends AppCompatActivity {
    private Button mBtAddData;
    private Button mBtUpData;
    private Button mBtDeleteData;
    private Button mBtQueryData;
   // private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_provider);
        mBtAddData = findViewById(R.id.bt_addData);
        mBtUpData = findViewById(R.id.bt_upData);
        mBtDeleteData = findViewById(R.id.bt_deleteData);
        mBtQueryData = findViewById(R.id.bt_queryData);
        mBtAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.huhaichang.learn3.provider/book");
                ContentValues contentValues = new ContentValues();
                contentValues.put("name","ABC");
                contentValues.put("author","ABC的书");
                contentValues.put("price","188");
                contentValues.put("pages","1048");
                Uri newUri = getContentResolver().insert(uri,contentValues);
              //  newId = newUri.getPathSegments().get(4);
                ToastUtil.showMsg(MyProviderActivity.this,"数据添加成功");
            }
        });
        mBtUpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.huhaichang.learn3.provider/book");
                ContentValues contentValues = new ContentValues();
                contentValues.put("price","215.8");
                getContentResolver().update(uri,contentValues,"id = ?",new String[]{"2"});//更新id="2"的书所在的那一行
                ToastUtil.showMsg(MyProviderActivity.this,"更新id=2的数据");
            }
        });
        mBtDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方法1
                Uri uri = Uri.parse("content://com.example.huhaichang.learn3.provider/book");
                getContentResolver().delete(uri,"id=?",new String[]{"1"});
                //方法2
                Uri uri1 = Uri.parse("content://com.example.huhaichang.learn3.provider/book/4");
                getContentResolver().delete(uri1,null,null);
                ToastUtil.showMsg(MyProviderActivity.this,"删除id=1和4的数据");
            }
        });
        mBtQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.huhaichang.learn3.provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                //有cursor了就赋值显示了
                while (cursor.moveToNext()) {
                    if (cursor != null) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Log.d("id: "+id,"书名:"+name+"  作者:"+author+" 价格:"+price+" 页数"+pages);
                    }
                }
                cursor.close();
            }
        });
    }
}
