package com.example.huhaichang.learn3.seven.datastorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.huhaichang.learn3.seven.widget.BookE;
import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.eight.widget.UserManage;
import com.example.huhaichang.learn3.widget.ToastUtil;

import org.litepal.LitePal;

import java.util.List;

public class LitePalActivity extends AppCompatActivity {
    private Button mBtCreateLitePal1;
    private Button mBtAddData;
    private Button mBtUpData;
    private Button mBtDeleteData;
    private Button mBtQueryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
        //application里的android:name="org.litepal.LitePalApplication"相当于
       // LitePal.initialize(LitePalActivity.this);
       mBtCreateLitePal1 =findViewById(R.id.bt_createLitePal1);
        mBtAddData = findViewById(R.id.bt_addData);
        mBtUpData = findViewById(R.id.bt_upData);
        mBtDeleteData = findViewById(R.id.bt_deleteData);
        mBtQueryData = findViewById(R.id.bt_queryData);
        mBtCreateLitePal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LitePal.getDatabase();

                ToastUtil.showMsg(LitePalActivity.this,"创建成功");
            }
        });
        mBtAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同一个对象只代表一个id  所以第一个保存无效；
                //androidManifest APPname设置了所有Activity都有Litepal的特性所以下面可以自动加入数据库
                BookE book =new BookE();
                book.setName("1号的书");
                book.setAuthor("1号");
                book.setPages(100);
                book.setPrice(18.5);
                book.save();
                //上面无效被覆盖
                book.setName("2号的书");
                book.setAuthor("2号");
                book.setPages(100);
                book.setPrice(18.5);
                book.save();
                UserManage userManage = new UserManage();
                userManage.setUser("54321");
                userManage.setPassword("54321");
                userManage.save();
                Toast.makeText(LitePalActivity.this,"数据添加成功",Toast.LENGTH_SHORT).show();
            }
        });
        mBtUpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookE book =new BookE();
                //更新部分
                book.setName("4号的书");
                book.setAuthor("4号");
                //假设要设置页数为0刚刚好没默认值
                //错误方法book.setPages(0);
                //正确方法
                book.setToDefault("pages");
                //判断更新对应的行数
                book.update(4);
                //book.updateAll("name = ? and author = ?","4号","4号的书"); //这个用来找范围的
                Toast.makeText(LitePalActivity.this,"4号数据更新成功",Toast.LENGTH_SHORT).show();
            }
        });
        mBtDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(BookE.class,"id = ?","3");
                LitePal.deleteAll(UserManage.class,"id = ?","3");
                Toast.makeText(LitePalActivity.this,"3号数据删除成功",Toast.LENGTH_SHORT).show();
            }
        });
        mBtQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BookE> books = LitePal.findAll(BookE.class);
                for(BookE book : books){
                    Log.d("id: "+book.getId(),"书名:"+book.getName()+"  作者:"+book.getAuthor()+" 价格:"+book.getPrice()+" 页数"+book.getPages());
                }
                List<UserManage> userManages =LitePal.findAll(UserManage.class);
                for(UserManage userManage : userManages){
                    Log.d("id: "+userManage.getId(),"账号："+userManage.getUser()+" 密码:"+userManage.getPassword());
                }
            }
        });
    }
}
