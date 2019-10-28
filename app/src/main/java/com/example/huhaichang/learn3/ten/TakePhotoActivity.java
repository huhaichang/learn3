package com.example.huhaichang.learn3.ten;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TakePhotoActivity extends AppCompatActivity {
    private Button mBtTakePhoto;
    private Button mBtChoosePhoto;
    private ImageView mIVPhoto;
    private Uri imageUri;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public  static final int TAKE_PHOTO = 1;
    public  static final int CHOOSE_PHOTO = 2;
    String y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        mBtTakePhoto = findViewById(R.id.bt_takePhoto);
        mIVPhoto = findViewById(R.id.iv_photo);
        mBtChoosePhoto = findViewById(R.id.bt_choosePhoto);
        sharedPreferences = getSharedPreferences("savePhotoPath",MODE_PRIVATE);
        Boolean a = sharedPreferences.getBoolean("read",false);
        if(a){
            String caremaPath = sharedPreferences.getString("camera","");
            String albumPath =sharedPreferences.getString("photoPath", "");
            //是从相册里保存
            if(caremaPath.equals("")) {
                Bitmap bitmap1 = BitmapFactory.decodeFile(albumPath);
                mIVPhoto.setImageBitmap(bitmap1);

            }
            //保存拍的照片
            if(albumPath.equals("")){
                Bitmap bitmap1 = BitmapFactory.decodeFile(caremaPath);
                mIVPhoto.setImageBitmap(bitmap1);
            }
        }
        mBtTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个File保存拍照后的图片 getExternalCacheDir()专门存放缓存数据的目录
                File outputPhoto = new File(getExternalCacheDir(),"output_photo.jpg");
                 y =outputPhoto.getPath();
                //保证初始化outputPhoto
                try {
                    if(outputPhoto.exists()){
                        outputPhoto.delete();
                    }
                    outputPhoto.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //版本适配 if是android：7及以上
                if(Build.VERSION.SDK_INT>=24){
                    //用到内容提供器 要注册
                    imageUri = FileProvider.getUriForFile(TakePhotoActivity.this,"com.example.huhaichang.learn3.fileprovider",outputPhoto);
                }else {
                    imageUri = Uri.fromFile(outputPhoto);
                }
                //启动相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });
        mBtChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断有没有权限
                if(ContextCompat.checkSelfPermission(TakePhotoActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED){
                    //申请授权
                    ActivityCompat.requestPermissions(TakePhotoActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    //有权限后就打开相册
                    openAlbum();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1 :
                //如果允许
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }
                else{
                    ToastUtil.showMsg(TakePhotoActivity.this,"你拒绝了这个权限");
                }
                break;
        }
    }

    private void openAlbum(){
         //打开相册 用了好几次就封装个方法
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                //获取图片实例
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        mIVPhoto.setImageBitmap(bitmap);
                        editor = sharedPreferences.edit();
                        editor.putBoolean("read",true);
                        editor.putString("photoPath","");
                        editor.putString("camera",y); //无法得到拍照的路径
                        editor.apply();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                break;
            case CHOOSE_PHOTO:
                //通过返回值得到照片实例
                    if(Build.VERSION.SDK_INT>=19){
                        //android4.4以上
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                break;
            default:
                break;
        }
    }
    //4.4以上Uri有3种类型 分别获取他们的路径
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        //判断是什么类型 分别对其操作
        if(DocumentsContract.isDocumentUri(this,uri)){  //1.如果是Documents类型的 通过Documents id处理
            String docId= DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id =docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads")
                        ,Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){  //2.如果是Content类型的 通过普通方式处理
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){     //3.如果是file类型的 直接获取路径
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }
    //4.4以下直接是Content类型的Uri
    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);

    }
    //获取路径方法(重复使用封装)
    private String getImagePath(Uri uri,String selection){
        String path =null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }
    //放置图片方法(重复使用封装)
    private void displayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mIVPhoto.setImageBitmap(bitmap);
            editor = sharedPreferences.edit();
            editor.putBoolean("read",true);
            editor.putString("camera","");
            editor.putString("photoPath",imagePath);
            editor.apply();
        }else{
            ToastUtil.showMsg(TakePhotoActivity.this,"获取图片失败");
        }
    }
}
