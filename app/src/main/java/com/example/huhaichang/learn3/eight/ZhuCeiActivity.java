package com.example.huhaichang.learn3.eight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.eight.widget.UserManage;
import com.example.huhaichang.learn3.widget.ToastUtil;

import org.litepal.LitePal;

import java.util.List;

public class ZhuCeiActivity extends AppCompatActivity {
   private EditText mEtUser,mEtPassword,mEtAgainPassword;
   private Button mBtZhuCei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_cei);
        mEtUser = findViewById(R.id.et_user);
        mEtPassword=findViewById(R.id.et_password);
        mEtAgainPassword=findViewById(R.id.et_againPassword);
        mBtZhuCei = findViewById(R.id.bt_zhuCei);
        mBtZhuCei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String zhangHao =   mEtUser.getText().toString();
              String password = mEtPassword.getText().toString();
              String againPassword = mEtAgainPassword.getText().toString();
              boolean a = true;
                List<UserManage> userManages = LitePal.findAll(UserManage.class);
                for(UserManage userManage : userManages){
                    if(userManage.getUser().equals(zhangHao)){
                        a = false;
                    }
                }
              if("".equals(zhangHao) ||  "".equals(password) || "".equals(againPassword)) {
                  ToastUtil.showMsg(ZhuCeiActivity.this,"请填入完整信息");

              }
              else {
                    if(password.equals(againPassword)) {
                        if (a) {
                         UserManage userManage = new UserManage();
                         userManage.setUser(zhangHao);
                         userManage.setPassword(password);
                          userManage.save();
                          ToastUtil.showMsg(ZhuCeiActivity.this, "注册成功");
                           Intent intent = new Intent(ZhuCeiActivity.this, LogonActivity.class);
                          startActivity(intent);
                          finish();
                      } else {
                        ToastUtil.showMsg(ZhuCeiActivity.this, "账号已存在");
                      }
                    }else {
                     ToastUtil.showMsg(ZhuCeiActivity.this,"2次密码不一样");
                    }
              }
            }
        });
    }
}
