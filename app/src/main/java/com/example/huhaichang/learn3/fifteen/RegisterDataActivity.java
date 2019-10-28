package com.example.huhaichang.learn3.fifteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.fifteen.widget.ConnectMysql;

import com.example.huhaichang.learn3.fifteen.widget.QueryMysql;
import com.example.huhaichang.learn3.widget.ToastUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RegisterDataActivity extends AppCompatActivity {
    private Button mBtRegister;
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private EditText mETName,mETZhanghao,mETPassword;
    private List<String> list = new ArrayList<>();
    private boolean ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);
        mBtRegister = findViewById(R.id.bt_getRegisterData);
        mETName = findViewById(R.id.et_name);
        mETZhanghao = findViewById(R.id.et_user);
        mETPassword = findViewById(R.id.et_password);
        //添加数据  //https://blog.csdn.net/weixin_37730482/article/details/77984417

        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String zhanghao = mETZhanghao.getText().toString();
                final String password = mETPassword.getText().toString();
                final String name = mETName.getText().toString();
                if(zhanghao.equals("") || password.equals("") || name.equals("")){
                    ToastUtil.showMsg(RegisterDataActivity.this,"请输入完整信息");
                }
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ok=false;
                            list = QueryMysql.query();

                            for(String a :list){
                                if(zhanghao.equals(a)){
                                    ok=true;    //账号存在
                                }
                            }
                           if(ok){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showMsg(RegisterDataActivity.this,"账号已存在注册失败");
                                    }
                                });
                           }else {
                               conn = ConnectMysql.openConn();
                              String sql="INSERT INTO userid (user,password,token,sign) VALUES (?,?,?,?)"; //test
                              // String sql="INSERT INTO userid (user,password,token) VALUES (?,?,?)";   //test1
                               try {
                                   ps = conn.prepareStatement(sql);
                                   ps.setString(1,zhanghao);
                                   ps.setString(2,password);
                                   ps.setString(3,name);
                                   ps.setString(4,hashCode()+""); //这个不能重复
                                   ps.executeUpdate();
                                   runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           ToastUtil.showMsg(RegisterDataActivity.this,"注册成功");
                                           finish();
                                       }
                                   });
                               } catch (SQLException e) {
                                   e.printStackTrace();
                               }
                               ConnectMysql.closeAll(conn,ps);
                           }
                        }
                    }).start();
                }



            }
        });
    }
}
