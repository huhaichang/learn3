package com.example.huhaichang.learn3.three;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class AlertDialogActivity extends AppCompatActivity {
    private  Button mbtnDialog1,mbtnDialog2,mbtnDialog3,mbtnDialog4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        mbtnDialog1 = findViewById(R.id.bt_alertDialog_1);
        mbtnDialog2 = findViewById(R.id.bt_alertDialog_2);
        mbtnDialog3 = findViewById(R.id.bt_alertDialog_3);
        mbtnDialog4 = findViewById(R.id.bt_alertDialog_4);
        OnClick onClick = new OnClick();
        mbtnDialog1.setOnClickListener(onClick);
        mbtnDialog2.setOnClickListener(onClick);
        mbtnDialog3.setOnClickListener(onClick);
        mbtnDialog4.setOnClickListener(onClick);

    }
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_alertDialog_1:
                    AlertDialog.Builder builder1 =new AlertDialog.Builder(AlertDialogActivity.this);
                    builder1.setTitle("本科").setMessage("分为一本和二本").setPositiveButton("是的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(AlertDialogActivity.this,"正确");
                        }
                    }).setNeutralButton("不知道", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(AlertDialogActivity.this,"去百度");
                        }
                    }).setNegativeButton("不是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(AlertDialogActivity.this,"错了");
                        }
                    }).setIcon(R.drawable.goodbay).show();
                    break;
                case R.id.bt_alertDialog_2:
                    AlertDialog.Builder builder2 =new AlertDialog.Builder(AlertDialogActivity.this);
                    final String array[] = new String[]{"男","女"};
                    //RadioButton的方法是setSingleChoiceItems
                    //选择后消失 dialog.dismiss();
                    //取消点击旁边灰色部分退出对话框.setCancelable(false)
                    builder2.setTitle("选择性别").setItems(array, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(AlertDialogActivity.this,array[which]);
                        }
                    }).show();
                    break;
                case R.id.bt_alertDialog_3:
                    AlertDialog.Builder builder3 =new AlertDialog.Builder(AlertDialogActivity.this);
                    final String intereing[] = new String[]{"羽毛球","篮球","兵乓球"};
                    final boolean select[] =new boolean[]{false,false,true};
                    builder3.setTitle("你的兴趣是什么").setMultiChoiceItems(intereing, select, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            ToastUtil.showMsg(AlertDialogActivity.this,intereing[which]+select[which]);
                        }
                    }).show();
                    break;
                case R.id.bt_alertDialog_4:
                    View view=LayoutInflater.from(AlertDialogActivity.this).inflate(R.layout.alert_dialog_item,null);
                    AlertDialog.Builder builder4 =new AlertDialog.Builder(AlertDialogActivity.this);
                    //下列设置点击事件
                    EditText et_username = view.findViewById(R.id.et_username);
                    EditText et_password = view.findViewById(R.id.et_password);
                    Button bt_denglu = view.findViewById(R.id.bt_denglu);
                    bt_denglu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.showMsg(AlertDialogActivity.this,"登录成功");
                        }
                    });
                    builder4.setTitle("请登录").setView(view).show();
                    break;
        }
    } }

    }

