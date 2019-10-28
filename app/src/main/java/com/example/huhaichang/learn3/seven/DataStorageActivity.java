package com.example.huhaichang.learn3.seven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.seven.datastorage.FileActivity;
import com.example.huhaichang.learn3.seven.datastorage.LitePalActivity;
import com.example.huhaichang.learn3.seven.datastorage.SQLiteActivity;
import com.example.huhaichang.learn3.seven.datastorage.SharePreferencesActivity;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {
  private Button mbtSharedPreferences,mbtFile;
  private Button mBtSQLite;
  private Button mBtLitePal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        mbtSharedPreferences = findViewById(R.id.bt_sharedpreferences);
        mbtFile = findViewById(R.id.bt_file);
        mBtSQLite =findViewById(R.id.bt_SQLite);
        mBtLitePal = findViewById(R.id.bt_litePal);
        mbtSharedPreferences.setOnClickListener(this);
        mbtFile.setOnClickListener(this);
        mBtSQLite.setOnClickListener(this);
        mBtLitePal.setOnClickListener(this);
        Log.i("b","onCreate");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_sharedpreferences:
                intent = new Intent(DataStorageActivity.this,SharePreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_file:
                intent = new Intent(DataStorageActivity.this,FileActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_SQLite:
                intent = new Intent(DataStorageActivity.this,SQLiteActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_litePal:
                intent = new Intent(DataStorageActivity.this,LitePalActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("b","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("b","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("b","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("b","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("b","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("b","onDestroy");
    }
}
