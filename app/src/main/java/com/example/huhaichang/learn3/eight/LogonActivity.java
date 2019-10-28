package com.example.huhaichang.learn3.eight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.eight.widget.UserManage;
import com.example.huhaichang.learn3.widget.BaseActivity;
import com.example.huhaichang.learn3.widget.LogUtil;
import com.example.huhaichang.learn3.widget.ToastUtil;

import org.litepal.LitePal;

import java.util.List;

public class LogonActivity extends BaseActivity {
    private EditText mETUser,mETPassword;
    private Button mBtLogon;
    private CheckBox mCBRememberPassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button mBtZhuCei;
    //记住密码是把数据放到数据库了
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        mETUser = findViewById(R.id.et_user);
        mETPassword = findViewById(R.id.et_password);
        mCBRememberPassword =findViewById(R.id.cb_rememberPassword);
        mBtLogon = findViewById(R.id.bt_logon);
        mBtZhuCei = findViewById(R.id.bt_zhuCei);
        sharedPreferences = getSharedPreferences("passwordku",MODE_PRIVATE);
        //或者
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean a = sharedPreferences.getBoolean("rememberPassword",false);
        if(a){
            mETUser.setText(sharedPreferences.getString("zhanghao",""));
            mETPassword.setText(sharedPreferences.getString("password",""));
            mCBRememberPassword.setChecked(true);
        }
        mBtLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputZhangHao = mETUser.getText().toString();
                String inputPassword = mETPassword.getText().toString();
                String getPassword="";

                List<UserManage> userManages = LitePal.findAll(UserManage.class);
                for(UserManage userManage : userManages){
                    if(userManage.getUser().equals(inputZhangHao)){
                        getPassword = userManage.getPassword();
                    }
                }

                if("".equals(inputZhangHao) || "".equals(inputPassword) ){
                    ToastUtil.showMsg(LogonActivity.this,"请输入账号或密码");
                }else{
                    if(inputPassword.equals(getPassword) )
                    {
                        editor = sharedPreferences.edit();
                        if(mCBRememberPassword.isChecked()){
                            editor.putBoolean("rememberPassword",true);
                            editor.putString("zhanghao",inputZhangHao);
                            editor.putString("password",inputPassword);
                        }else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(LogonActivity.this,Logon1Activity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        ToastUtil.showMsg(LogonActivity.this,"账号或密码错误");
                    }
                }
            }
        });
        mBtZhuCei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogonActivity.this,ZhuCeiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("LogonActivity", "onDestroy: ");
    }
}
