package com.example.huhaichang.learn3.two.recyclerview;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class LinearRecycleViewActivity extends AppCompatActivity {
 private RecyclerView lrv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycle_view);
        lrv= findViewById(R.id.lrv_1);
        lrv.setLayoutManager(new LinearLayoutManager(LinearRecycleViewActivity.this));
        //放置下划线
        lrv.addItemDecoration(new MyDecoration());

       lrv.setAdapter(new LinearRecycleAdapter(LinearRecycleViewActivity.this,new LinearRecycleAdapter.OnItemClickListener() {
           @Override
           public void onClick(int pos) {
               if(pos==0){
                   ToastUtil.showMsg(LinearRecycleViewActivity.this,"124");}
                   else {
               Toast.makeText(LinearRecycleViewActivity.this,"click"+pos,Toast.LENGTH_SHORT).show();}
           }
       }));
    }
//设置底部间隔
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight2));
        }
    }
}
