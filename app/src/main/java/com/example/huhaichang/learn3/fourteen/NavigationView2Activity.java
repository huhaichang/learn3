package com.example.huhaichang.learn3.fourteen;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class NavigationView2Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView mTVMail;
    private Button mBtEdit;
    private Button mBtBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view2);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.dl_1);
        navigationView =findViewById(R.id.nav_view);
        mBtEdit=findViewById(R.id.bt_edit);
        mBtBack = findViewById(R.id.bt_99);
        //修改头部布局ui
        View view = navigationView.getHeaderView(0);
        mTVMail = view.findViewById(R.id.name);
        mTVMail.setText("HHC");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true); //开启导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.san);
        }
        navigationView.setCheckedItem(R.id.nav_call);//初始化默认第一个Call 应为Group了 所以你选择的会变颜色
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                        ToastUtil.showMsg(NavigationView2Activity.this,"你点击了电话");
                        break;
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawerLayout.closeDrawers();
                    }
                }, 300);
                return true;
            }
        });
        mBtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Intent intent = new Intent(NavigationViewActivity.this,FourteenMainActivity.class);
                // startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //导航按钮的id
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return true;
    }
}
