package com.example.huhaichang.learn3.two.recyclerview;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.huhaichang.learn3.R;

public class PubuRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubu_recycler_view);
        recyclerView = findViewById(R.id.prv_1);
        //2列
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置分割线
        recyclerView.addItemDecoration(new MyDecoration2());

        recyclerView.setAdapter(new StaggeredGridRecycleViewAdapter(PubuRecyclerViewActivity.this, new StaggeredGridRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(PubuRecyclerViewActivity.this,"click"+pos,Toast.LENGTH_SHORT).show();
            }
        }));

    }
    class MyDecoration2 extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int a =getResources().getDimensionPixelOffset(R.dimen.dividerHeight2);
            outRect.set(a, a, getResources().getDimensionPixelOffset(R.dimen.dividerHeight2),a);
        }
    }
}
