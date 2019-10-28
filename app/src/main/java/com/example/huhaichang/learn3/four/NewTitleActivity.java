package com.example.huhaichang.learn3.four;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class NewTitleActivity extends AppCompatActivity {
    private List<News> mNewsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    String news3content ="新浪娱乐讯 据台湾媒体报道，漫威工作室（Marvel Studio）于21日早上8点15分在“SDCC圣地亚哥动漫展’”办记者会，漫威总裁凯文·费奇（Kevin Feige）正式宣布“漫威电影宇宙第4阶段”计划，《雷神4》（Thor 4）确定推出，并公布副标题为“Love and Thunder”，而且有2个女主角，“简·福斯特”娜塔莉·波特曼（ Natalie Portma）确定回归。";
    private String[] title = new String[]{"1","2","“锤哥”回归《雷神4》 波特曼将出演“女雷神”","4","5","6","7","8","9","10"};
    private String[] content = new String[]{"1","2",news3content,"4","5","6","7","8","9","10"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_title);


        mRecyclerView = findViewById(R.id.rv_newsTitle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        NewsTitleAdapter adapter =new NewsTitleAdapter( mNewsList,new NewsTitleAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                ToastUtil.showMsg(NewTitleActivity.this,(pos+1)+"号新闻");
                Intent intent = new Intent(NewTitleActivity.this,NewsContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",title[pos]);
                bundle.putString("content",content[pos]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        for(int i =0;i<=9;i++){
            News news = new News();
                news.setTitle("News:  "+title[i]);
            mNewsList.add(news);

        }
    }

}
