package com.example.huhaichang.learn3.sixteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class SearchActivity extends AppCompatActivity {
    private android.support.v7.widget.SearchView mSVSearch;
    private ListView listView;
    private String[] context =new String[]{"aaa","af11531","basf","afsaf","casfs","cfwf","安心","a随时"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSVSearch = findViewById(R.id.sv_search);
        listView = findViewById(R.id.lv_search);
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,context));
        listView.setTextFilterEnabled(true);//设置启用过滤
        mSVSearch.setIconifiedByDefault(false);//禁止自动缩小图标
        //mSVSearch.setSubmitButtonEnabled(true);//显示搜索按钮(一个右箭头) 不要
        mSVSearch.setQueryHint("查找");
        mSVSearch.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {    //点击搜索方法后 执行相应代码
               /* if(listView.getCount()==1){
                    Intent intent = new Intent(SearchActivity.this,SixteenMainActivity.class);
                    intent.putExtra("context",query);
                    startActivity(intent);
                ToastUtil.showMsg(SearchActivity.this,"你选择了:"+query);
                }else{
                    ToastUtil.showMsg(SearchActivity.this,"搜索结果不符合");
                }*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {   //每次String改变
               if(TextUtils.isEmpty(newText)){
        //可以设置历史记录 把listView隐藏 显示listView2;
                   // listView.clearTextFilter();//如果空就全部显示

                }else{
                    listView.setFilterText(newText); //包含newText的显示出来  就是过滤的以上
                }

                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this,SixteenMainActivity.class);
                TextView textView = view.findViewById(android.R.id.text1);
                intent.putExtra("context",textView.getText());  //天气数据库 可以传内容为context[position] 所在的id
                startActivity(intent);
                finish();
            }
        });
    }
}
