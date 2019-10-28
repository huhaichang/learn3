package com.example.huhaichang.learn3.fifteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.seven.widget.BookE;
import com.example.huhaichang.learn3.seven.widget.BookZ;

public class Intent2Activity extends AppCompatActivity {
    private TextView mTt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);
        mTt1 = findViewById(R.id.tv_1);
        if(getIntent().getSerializableExtra("bookE_data")!=null){
            BookE bookE =  (BookE)getIntent().getSerializableExtra("bookE_data");
            String a = "ID="+bookE.getId()+"\n bookName="+bookE.getName();
            mTt1.setText(a);
        }
        if(getIntent().getParcelableExtra("bookZ_data")!=null) {
            BookZ bookZ =getIntent().getParcelableExtra("bookZ_data");
            String b = "ID=" + bookZ.getId() + "\n bookName=" + bookZ.getName();
            mTt1.setText(b);
        }
    }
}
