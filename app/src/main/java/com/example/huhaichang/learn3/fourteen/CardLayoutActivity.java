package com.example.huhaichang.learn3.fourteen;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardLayoutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<StringTest> list = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private HuaAdapter adapter;
    private int r=0;

    int index;
    //由于要用数组打乱刷新 就不直接用列表了
    private StringTest[] stringTests ={new StringTest("苹果",R.drawable.asdf)
            ,new StringTest("香蕉"),new StringTest("葡萄")
            ,new StringTest("芒果"),new StringTest("百香果")
            ,new StringTest("无花果"),new StringTest("橘子")
            ,new StringTest("樱桃"),new StringTest("菠萝")
            ,new StringTest("西瓜"),new StringTest("芭乐")};
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rv_hua);
        swipeRefreshLayout =findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshHua();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(CardLayoutActivity.this,2));
        //花用水果代替2种构造方法
       /* list.add(new StringTest("苹果",R.drawable.asdf));
        list.add(new StringTest("香蕉"));
        list.add(new StringTest("葡萄"));
        list.add(new StringTest("芒果"));
        list.add(new StringTest("百香果"));
        list.add(new StringTest("无花果"));
        list.add(new StringTest("橘子"));
        list.add(new StringTest("樱桃"));
        list.add(new StringTest("菠萝"));
        list.add(new StringTest("西瓜"));*/
       for (r=0;r<stringTests.length;r++){
           list.add(stringTests[r]);
        }
        adapter=new HuaAdapter(CardLayoutActivity.this,list, new HuaAdapter.Listen() {
            @Override
            public void onclick(int pos) {
                ToastUtil.showMsg(CardLayoutActivity.this,""+pos);
            }
        });
        recyclerView.setAdapter(adapter);
    }
    private void refreshHua(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //随机改变位置
                        initHua();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initHua(){
        list.clear();
        for(int i =0;i<stringTests.length;i++) {
            //随机打乱数组 重新导入列表中   //会重复
            // if(stringTests[i].getName())

            Random random = new Random();
            index = random.nextInt(stringTests.length);
            list.add(stringTests[index]);
        }
    }
}

