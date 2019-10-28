package com.example.huhaichang.learn3.sixteen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.example.huhaichang.learn3.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huhaichang on 2019/9/13.
 */

public class PlaneView extends View {
    public float x,y;
    private Paint paint = new Paint();
    private Bitmap plane1,plane2; //图片
    private int a;
    public PlaneView(Context context) {
        super(context);
        plane1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        plane2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane3);
        //启动定时器切换飞机图片，
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                a++;
                PlaneView.this.invalidate();  //相当于调用onDraw
                //invalidate();
            }
        },0,100);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(a%2==0?plane1:plane2,x,y,paint);

    }
    //触摸事件监听跟速度有关 要我们自己设置就在activity去完成了
}
