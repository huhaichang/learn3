package com.example.huhaichang.learn3.fifteen.AD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class AddActivity extends AppCompatActivity {
    private EditText mETAdd;
    private Button mBtAddOk;
    private SharedPreferences sharedPreferences; //读取数据
    private SharedPreferences.Editor editor;   //放入数据
    private String a,b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mETAdd =findViewById(R.id.et_add);
        mBtAddOk = findViewById(R.id.bt_addOk);
        sharedPreferences = getSharedPreferences("adPoint",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        a=sharedPreferences.getString("1","");
        b=sharedPreferences.getString("2","");
        c=sharedPreferences.getString("3","");
        d=sharedPreferences.getString("4","");
        mBtAddOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mETAdd.getText().toString().equals("")){
                    ToastUtil.showMsg(AddActivity.this,"请输入添加内容");
                }else {
                    if(a.equals("")){
                        editor.putString("1",mETAdd.getText().toString());
                    }else if(b.equals("")){
                        editor.putString("2",mETAdd.getText().toString());
                    }else if(c.equals("")){
                        editor.putString("3",mETAdd.getText().toString());
                    }else if(d.equals("")){
                        editor.putString("4",mETAdd.getText().toString());
                    }
                    editor.apply();
                    Intent intent = new Intent(AddActivity.this,ADActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
