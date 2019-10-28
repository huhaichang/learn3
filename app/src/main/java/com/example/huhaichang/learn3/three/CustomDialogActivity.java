package com.example.huhaichang.learn3.three;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.CustomDialog;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class CustomDialogActivity extends AppCompatActivity {
private Button mCustomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        mCustomDialog = findViewById(R.id.bt_customDialog_1);
        mCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog =new CustomDialog(CustomDialogActivity.this);
                customDialog.setTitle("提示").setMessage("是否删除此项").setCancel("取消", new CustomDialog.IOnCancelListener() {
                    @Override
                    public void onCancelClick(CustomDialog dialog) {
                        ToastUtil.showMsg(CustomDialogActivity.this,"取消");

                    }
                }).setConfirm("确认", new CustomDialog.IOnConfirmListener() {
                    @Override
                    public void onConfirmClick(CustomDialog dialog) {
                        ToastUtil.showMsg(CustomDialogActivity.this,"确定");

                    }
                }).show();
            }
        });
    }
}
