package com.example.huhaichang.learn3.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by huhaichang on 2019/7/14.
 */

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //返回值为true时禁止其他地方调用onTouchEvent例如：EventActivity中的 该控件 下的所有onTouch指令都无法执行
    //当为false时则都会执行

    //优先级第二
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /* if(event.getAction()==MotionEvent.ACTION_UP)
        {
            Log.d("MyButton", "---onTouchEvent--- ");
            return false;
        }
        else
            return false;*/
       switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("MyButton", "---onTouchEventDown--- ");
                break;
           case MotionEvent.ACTION_UP:
               Log.d("MyButton", "---onTouchEventUp--- ");
               break;
        }
        return false;
    }
}
