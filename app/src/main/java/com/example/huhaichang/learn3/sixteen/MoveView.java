package com.example.huhaichang.learn3.sixteen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huhaichang on 2019/9/13.
 */

public class MoveView extends View {
    private float x =40f;
    private float y =50f; //初始位置
    private Paint paint =new Paint();
    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawCircle(x,y,30f,paint);//弄个圆  2个float（位置）1个float(圆度圆大小) 一个Paint（属性 颜色等）
    }

    //现在要球移动 就改变x，y;使它等于手指触碰的位置

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=event.getX();
        y=event.getY();
        invalidate(); //刷新重绘 一定要 //相当于调用onDraw
        return true;
    }
}
