package com.example.huhaichang.learn3.nine;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PhoneInformationActivity extends AppCompatActivity {
    private ListView contactsView;
    ArrayAdapter<String> adapter;
    List<String> contactsList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_information);
        //把adapter适配好
        contactsView = findViewById(R.id.contacts_view);
        //把列表的每一个元素作为一个item(item=String的特殊快捷用法)
        adapter = new ArrayAdapter<String>(PhoneInformationActivity.this,android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);
        contactsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
           //提取String类型contactsList.get(position)中的第二行
                BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(contactsList.get(position).getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
                StringBuffer stringBuffer = new StringBuffer();
                String line ;
                int a=0;
                try {
                    while ((line = br.readLine())!=null){
                        if(!line.trim().equals("")){
                            if(a==1){
                            stringBuffer.append(line);
                            }
                            a++;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final String number = stringBuffer.toString();
                AlertDialog.Builder builder= new AlertDialog.Builder(PhoneInformationActivity.this);
                builder.setTitle("提示").setMessage("要拨打电话号码为"+number+"的电话吗").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PhoneInformationActivity.this,CallPhoneActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("number",number.toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).show();

            }
        });



        //如果读联系人权限未授权
        if(ContextCompat.checkSelfPermission(PhoneInformationActivity.this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            //申请授权
            ActivityCompat.requestPermissions(PhoneInformationActivity.this,new String[]{Manifest.permission.READ_CONTACTS},2);
        }
        else{
            readContacts();
        }
    }
    //读取联系人
   private void readContacts(){
       Cursor cursor = null;
       try
       {
           //查询电话簿数据
       cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
       if(cursor!=null){
           //1个1个读取
           while(cursor.moveToNext()){
               String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
               String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
               //放入列表里
               contactsList.add(name+"\n"+number);
           }
       }
       adapter.notifyDataSetChanged();

       }catch(Exception e){
            e.printStackTrace();
       }finally {
           if(cursor !=null){
               cursor.close();
           }
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 2:
                //如果允许
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }
                else{
                    ToastUtil.showMsg(PhoneInformationActivity.this,"你拒绝了这个权限");
                }
                break;
            default:
        }
    }
}
