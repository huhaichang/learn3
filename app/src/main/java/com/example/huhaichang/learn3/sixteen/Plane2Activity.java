package com.example.huhaichang.learn3.sixteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class Plane2Activity extends AppCompatActivity {
    private int speed = 10;
    private PlaneView planeView;
    private RelativeLayout root;
    DisplayMetrics metrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane2);
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取窗口管理器 得到屏幕高低
        WindowManager windowManager =getWindowManager();
        Display display =windowManager.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        root =findViewById(R.id.rt_5);
        planeView = new PlaneView(this);
        planeView.x = metrics.widthPixels/2; //初始位置中间
        planeView.y = metrics.heightPixels-200;
        planeView.setOnTouchListener(new MyTouchListener());
        root.addView(planeView);
    }
    class MyTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getX()<metrics.widthPixels/8){
                if(planeView.x>(-60)) {
                    planeView.x -= speed;//左移
                }
                Log.d("11",planeView.x+"");
            }
            if(event.getX()>metrics.widthPixels*7/8){
                planeView.x +=speed;//右移
            }
            if(event.getY()<metrics.heightPixels/8){
                planeView.y-=speed;//下移
            }
            if(event.getY()>metrics.heightPixels*7/8){   //最上面的8/1部分
                planeView.y+=speed;//上移
            }

            return true;
        }
    }
}
