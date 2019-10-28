package com.example.huhaichang.learn3.fifteen.AD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    private LinearLayout mLLDelete;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView mTvPoint;
    private Button mBtok,mBt156;
    private String x;
    private List<String> list = new ArrayList<>();  //放入内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        mLLDelete = findViewById(R.id.ll_delete);
        mBt156 =findViewById(R.id.bt_156);
        sharedPreferences = getSharedPreferences("adPoint",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        list.add(sharedPreferences.getString("1",""));
        list.add(sharedPreferences.getString("2",""));
        list.add(sharedPreferences.getString("3",""));
        list.add(sharedPreferences.getString("4",""));
        mBt156.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DeleteActivity.this,ADActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mLLDelete.removeAllViews();

        for(int i = 0;i<list.size();i++) {
            x=list.get(i);
            if(!x.equals("")){
            View view = LayoutInflater.from(DeleteActivity.this).inflate(R.layout.points_item, mLLDelete, false);
            mTvPoint = view.findViewById(R.id.tv_point);
            mBtok = view.findViewById(R.id.bt_ok);
            mTvPoint.setText("移除:"+x);
                final int finalI = i;
                mBtok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString(""+ (finalI+1),"");
                    editor.apply();
                    Intent intent =new Intent(DeleteActivity.this,ADActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            mLLDelete.addView(view);
        }
        }
    }
}
