package com.example.huhaichang.learn3.seven.datastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class SharePreferencesActivity extends AppCompatActivity {
  private EditText mEtname;
  private Button mbtSave,mbtShow;
  private TextView mtvShow;                        //键值对方式
  private SharedPreferences mSharedPreferences;   //类似写（通过键写到TextView）
  private SharedPreferences.Editor mEditor;       //类似读（读编辑框的内容给键）
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);
        mEtname = findViewById(R.id.et_name);
        mbtSave = findViewById(R.id.bt_save);
        mbtShow = findViewById(R.id.bt_show);
        mtvShow = findViewById(R.id.tv_show);
        //通过键值对写入b(a,b) ， 通过a获取b    //data 文件名   MODE_PRIVATE只能本APP进行读写,否则数据有安全隐患
        mSharedPreferences =getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
          //将mEtname.getText().toString())数据写入（键值对方式）
        mbtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(SharePreferencesActivity.this,"保存成功");
                    mEditor.putString("name",mEtname.getText().toString());
                   // mEditor.clear();清空文件
                    mEditor.apply();  //提交生效（异步）  或者mEditor.commit(同步,也可以相对比较差一丢丢);
            }
        });
        //数据读取到TextView
        mbtShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mtvShow.setText(mSharedPreferences.getString("name",""));
            }
        });
    }
}
