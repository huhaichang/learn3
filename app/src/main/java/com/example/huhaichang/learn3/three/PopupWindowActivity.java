package com.example.huhaichang.learn3.three;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class PopupWindowActivity extends AppCompatActivity {
   private Button mbtpopop;
   private PopupWindow mpopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mbtpopop = findViewById(R.id.bt_popup);
        mbtpopop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(PopupWindowActivity.this).inflate(R.layout.layout_popup_window,null);
                TextView sfz =view.findViewById(R.id.tv_sfz);
                TextView xsz =view.findViewById(R.id.tv_xsz);
                TextView jrz =view.findViewById(R.id.tv_jrz);
                TextView gzz =view.findViewById(R.id.tv_gzz);
                TextView other =view.findViewById(R.id.tv_other);
                sfz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(PopupWindowActivity.this,"身份证");
                        mbtpopop.setText("身份证");
                        mpopupWindow.dismiss();
                    }
                });
                xsz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(PopupWindowActivity.this,"学生证");
                        mbtpopop.setText("学生证");
                        mpopupWindow.dismiss();
                    }
                });
                jrz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(PopupWindowActivity.this,"军人证");
                        mbtpopop.setText("军人证");
                        mpopupWindow.dismiss();
                    }
                });
                gzz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(PopupWindowActivity.this,"工作证");
                        mbtpopop.setText("工作证");
                        mpopupWindow.dismiss();
                    }
                });
                other.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(PopupWindowActivity.this,"其它");
                        mbtpopop.setText("其它");
                        mpopupWindow.dismiss();
                    }
                });
                mpopupWindow =new PopupWindow(view,mbtpopop.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                mpopupWindow.setOutsideTouchable(true);
                mpopupWindow.setFocusable(true);
                mpopupWindow.showAsDropDown(mbtpopop);
            }
        });

    }
}
