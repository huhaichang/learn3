package com.example.huhaichang.learn3.two.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.huhaichang.learn3.R;

public class GridRecycleViewActivity extends AppCompatActivity {
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycle_view);
        recyclerView = findViewById(R.id.grv_1);
        //设置3列
        recyclerView.setLayoutManager(new GridLayoutManager(GridRecycleViewActivity.this,3));
        recyclerView.setAdapter(new GridRecycleViewAdapter(GridRecycleViewActivity.this, new GridRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(GridRecycleViewActivity.this,"Click"+pos,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
