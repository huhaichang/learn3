package com.example.huhaichang.learn3.four;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class AActivity extends AppCompatActivity {
  EditText met_user,met_password,met_again_password,met_email_address;
  Button mbt_confirm,mbt_self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        //Activity的4种启动模式 Standard,singleTop（容器顶）,singleTask(容器内),singleInstance(launchMode属性声明)
        //  Standard 一直重新实例化（onCreate）      singleInstance 创建后重新一直重复使用不重新实例化hash值不变（onNewIntent）
        //名字默认包名，改名字taskAffinity声明,每一个名字相当于一个容器,
        Log.d("AActivity","----onCreate----");
        Log.d("AActivity","taskID: "+getTaskId()+",  hash:"+hashCode());
        logtaskName();
       met_user = findViewById(R.id.et_user);
       met_password = findViewById(R.id.et_password);
       met_again_password = findViewById(R.id.et_again_password);
       met_email_address = findViewById(R.id.et_email_address);
       mbt_confirm = findViewById(R.id.bt_confirm);
       mbt_self = findViewById(R.id.bt_self);
       mbt_self.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(AActivity.this,AActivity.class);
               startActivity(intent);
           }
       });
       mbt_confirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //如下可以简化
               /* String user = met_user.getText().toString();
                String password = met_password.getText().toString();
                String repassword = met_again_password.getText().toString();
                String email = met_email_address.getText().toString();*/
               if(!"".equals(met_user.getText().toString()) && !"".equals(met_password.getText().toString()) &&
                       !"".equals(met_again_password.getText().toString()) && !"".equals(met_email_address.getText().toString())) {
                   if (!met_password.getText().toString().equals(met_again_password.getText().toString())) {
                       ToastUtil.showMsg(AActivity.this, "两次输入的密码不一样请重新输入");
                   } else {
                       Intent intent = new Intent(AActivity.this, BActivity.class);
                       Bundle bundle = new Bundle();
                       bundle.putString("user", met_user.getText().toString());
                       bundle.putString("password", met_password.getText().toString());
                       bundle.putString("againpassword", met_again_password.getText().toString());
                       bundle.putString("E-mail", met_email_address.getText().toString());
                       // intent和bundle的关联方法
                       intent.putExtras(bundle);
                       //或者不用bundle
                       intent.putExtra("userTest",met_user.getText().toString());
                       startActivity(intent);
                   }
               }
               else {
                   ToastUtil.showMsg(AActivity.this,"请将注册信息填完整");
               }
           }
       });
    }
//不重新实例化Activity
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("AActivity","----onNewIntent----");
        Log.d("AActivity","taskID: "+getTaskId()+",  hash:"+hashCode());
        logtaskName();
    }

    private  void logtaskName(){
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            Log.d("AActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
