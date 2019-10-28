package com.example.huhaichang.learn3.two.recyclerview;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.huhaichang.learn3.R;

public class HoriRecycleViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hori_recycle_view);
        recyclerView = findViewById(R.id.hrv_1);
        //RecycleView相应的东西
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HoriRecycleViewActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //放置下划线
        recyclerView.addItemDecoration(new MyDecoration1());


        recyclerView.setAdapter(new HorRecycleViewAdapter(HoriRecycleViewActivity.this, new HorRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(HoriRecycleViewActivity.this, "click" + pos, Toast.LENGTH_SHORT).show();
            }
        }));
    }
        class MyDecoration1 extends RecyclerView.ItemDecoration {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight), 0);
            }

        }
    }