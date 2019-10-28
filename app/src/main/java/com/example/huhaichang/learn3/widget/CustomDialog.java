package com.example.huhaichang.learn3.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

/**
 * Created by huhaichang on 2019/6/14.
 */

public class CustomDialog extends Dialog implements View.OnClickListener{
   private TextView mtvTitle,mtvMessage,mtvCancel,mtvConfirm;
    private String title,message,cancel,confirm;
    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;


    public CustomDialog(@NonNull Context context) {
        super(context);
    }
    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getTitle(){
        String title = this.title;
        return title;
    }

    public CustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomDialog setCancel(String cancel,IOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener=listener;
        return this;
    }

    public CustomDialog setConfirm(String confirm,IOnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener=listener;
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);
        //设置框的宽度
        WindowManager m =getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        Point size =new Point();
        d.getSize(size);
        p.width =(int)(size.x*0.8);
        getWindow().setAttributes(p);

        mtvTitle =findViewById(R.id.tv_title);
        mtvMessage =findViewById(R.id.tv_message);
        mtvCancel =findViewById(R.id.tv_cancel);
        mtvConfirm =findViewById(R.id.tv_confirm);
        mtvTitle.setText(title);
        mtvMessage.setText(message);
        mtvCancel.setText(cancel);
        mtvConfirm.setText(confirm);
        /*if(!TextUtils.isEmpty(title)){//!TextUtils.isEmpty(title)
            mtvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(message)){
            mtvMessage.setText(message);
        }
        if(!TextUtils.isEmpty(cancel)){
            mtvCancel.setText(cancel);
        }
        if(!TextUtils.isEmpty(confirm)){
            mtvConfirm.setText(confirm);
        }*/
      //this 代表这个类的对象即 CustomDialog a;  this就代表a;
        mtvCancel.setOnClickListener(this);
        mtvConfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                /*if(cancelListener!=null){
                    cancelListener.onCancelClick(this);
                }*/
                cancelListener.onCancelClick(this);
                dismiss();
                break;
            case R.id.tv_confirm:
                /*if(confirmListener!=null){
                    confirmListener.onConfirmClick(this);
                }*/
                confirmListener.onConfirmClick(this);
                dismiss();
                break;
        }
    }


    //设置接口使外部调用此类是有点击事件
    public  interface IOnCancelListener{
        void onCancelClick(CustomDialog dialog);
    }
    public  interface IOnConfirmListener{
        void onConfirmClick(CustomDialog dialog);
    }
}
