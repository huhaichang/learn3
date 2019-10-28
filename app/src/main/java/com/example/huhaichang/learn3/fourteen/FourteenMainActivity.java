package com.example.huhaichang.learn3.fourteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class FourteenMainActivity extends AppCompatActivity {
    private Button mBtToolbar;
    private Button mBtSlideMenu;
    private Button mBtNavigationView;
    private Button mBtNavigation2View;
    private Button mBtFAButton;
    private Button mBtCardLayout;
    private Button mBtCollapsingToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourteen_main);
        mBtToolbar = findViewById(R.id.bt_toolbar);
        mBtSlideMenu = findViewById(R.id.bt_slideMenu);
        mBtNavigationView =findViewById(R.id.bt_navigationView);
        mBtFAButton =findViewById(R.id.bt_floatingActionButton);
        mBtCardLayout = findViewById(R.id.bt_cardLayout);
        mBtCollapsingToolbar = findViewById(R.id.bt_collapsingToolbar);
        mBtNavigation2View =findViewById(R.id.bt_navigation2View);
        mBtToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourteenMainActivity.this,ToolbarActivity.class);
                startActivity(intent);
            }
        });
        mBtSlideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourteenMainActivity.this,SlideMenuActivity.class);
                startActivity(intent);
            }
        });
        mBtNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourteenMainActivity.this,NavigationViewActivity.class);
                startActivity(intent);
            }
        });
        mBtNavigation2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourteenMainActivity.this,NavigationView2Activity.class);
                startActivity(intent);
            }
        });
        mBtFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourteenMainActivity.this,FloatingActionButtonActivity.class);
                startActivity(intent);
            }
        });
        mBtCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourteenMainActivity.this,CardLayoutActivity.class);
                startActivity(intent);
            }
        });
        mBtCollapsingToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourteenMainActivity.this,CollapsingToolbarActivity.class);
                startActivity(intent);
            }
        });
    }
}
