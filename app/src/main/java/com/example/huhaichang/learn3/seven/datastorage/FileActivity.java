package com.example.huhaichang.learn3.seven.datastorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {
    private EditText mEtname;
    private Button mbtSave,mbtShow;
    private TextView mtvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        mEtname = findViewById(R.id.et_name);
        mbtSave = findViewById(R.id.bt_save);
        mbtShow = findViewById(R.id.bt_show);
        mtvShow = findViewById(R.id.tv_show);
        mbtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(mEtname.getText().toString());
                ToastUtil.showMsg(FileActivity.this,"保存成功");
            }
        });
        mbtShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = read();
                mtvShow.setText(a);
            }
        });
    }

    //存储数据 通过openFileOutput实例化FileOutputStream
    //            然后直接write方法写入(输出，存储)
    public void save(String content){
        FileOutputStream fileout = null;
        try {
            //1.内部存储(就直接放在专门的FileOutput文件下)
        fileout = openFileOutput("text.txt",MODE_PRIVATE);

        //2.外部存储(自定义位置)  在sd卡下新建文件夹skypan 并创建text.txt文件 代码如下
                              //Environment.getExternalStorageDirectory() 在SD卡下
           /* File dir = new File(Environment.getExternalStorageDirectory(),"skypan");
            if(!dir.exists()){
                dir.mkdir();  //确认创建文件夹
            }
            File file = new File(dir,"text.txt");
            if(!file.exists()){
                file.createNewFile(); //确认创建文件
            }
            fileout = new FileOutputStream(file);*/

            fileout.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileout!=null){
            try {
                fileout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
    }
    //读取数据（输入流） 定义FileInputStream len buffer[]
    //                 通过len=fileIn.read(buffer)建立联系读到数组里
    //                 s =new String(buffer,0,buffer.length);把数组放到s里；
    public String read(){
        FileInputStream fileIn = null;
       String s="1";
        try {
            //内部存储读取
            fileIn = openFileInput("text.txt");
            //外部存储读取                                                                     +File.separator+"skypan" 相当于+"/skypan"
            /*File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"skypan","text.txt");
            fileIn = new FileInputStream(file);*/

            //1.由于是一个一个的读所以用数组来获取输出的内容
            byte buffer[] = new byte[1024];

            //2.通过len来判断是否读完
            int len = 0;

            //3.搞个接收的东西 sb(StringBuffer) 或者s（直接赋值）
            StringBuffer sb = new StringBuffer();  //方法3.1
            //当len=-1时表示读完了
            while((len=fileIn.read(buffer))!=-1){
                //sb.append(new String(buffer,0,len));//方法3.1
                s=new String(buffer,0,buffer.length); //方法3.2
            }
            return s;//方法3.2
            // return sb.toString();//方法3.1
        }  catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileIn!=null){
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
