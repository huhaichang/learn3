package com.example.huhaichang.learn3.fifteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.seven.widget.BookE;
import com.example.huhaichang.learn3.seven.widget.BookZ;

public class Intent1Activity extends AppCompatActivity {
    private Button mBtIntent147,mBtParcelable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);
        mBtIntent147 = findViewById(R.id.bt_intent147);
        mBtParcelable = findViewById(R.id.bt_intent478);
        mBtIntent147.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent1Activity.this,Intent2Activity.class);
               BookE bookE = new BookE();
                bookE.setId(1235);
                bookE.setName("HHC");
                intent.putExtra("bookE_data",bookE);
                startActivity(intent);
            }
        });
        mBtParcelable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent1Activity.this,Intent2Activity.class);
                BookZ bookZ = new BookZ();
                bookZ.setId(18);
                bookZ.setName("HXZW");
                intent.putExtra("bookZ_data",bookZ);
                startActivity(intent);
            }
        });
    }
}
