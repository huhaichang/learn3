package com.example.huhaichang.learn3;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.eight.EightMainActivity;
import com.example.huhaichang.learn3.eleven.ElevenMainActivity;
import com.example.huhaichang.learn3.fifteen.FifteenMainActivity;
import com.example.huhaichang.learn3.five.FiveMainActivity;
import com.example.huhaichang.learn3.four.FourMainActivity;
import com.example.huhaichang.learn3.fourteen.FourteenMainActivity;
import com.example.huhaichang.learn3.nine.NineMainActivity;
import com.example.huhaichang.learn3.seven.SevenMainActivity;
import com.example.huhaichang.learn3.sixteen.SixteenMainActivity;
import com.example.huhaichang.learn3.ten.TenMainActivity;
import com.example.huhaichang.learn3.thirteen.ThirteenMainActivity;
import com.example.huhaichang.learn3.three.ThreeMainActivity;
import com.example.huhaichang.learn3.twelve.TwelveMainActivity;
import com.example.huhaichang.learn3.two.TwoMainActivity;
import com.example.huhaichang.learn3.widget.ToastUtil;

public class MainActivity extends AppCompatActivity {
    private Button twoButton,threeButton,fourButton,fiveButton,sevenButton,eightButton,nineButton;
    private Button tenButton,elevenButton,twelveButton,thirteenButton,fourteenButton,fifteenButton;
    private Button sixteenButton;
    private TextView tvtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvtest = findViewById(R.id.tv_1);
        twoButton =findViewById(R.id.bt_two);
        threeButton =findViewById(R.id.bt_three);
        fourButton = findViewById(R.id.bt_four);
        fiveButton = findViewById(R.id.bt_five);
        sevenButton = findViewById(R.id.bt_seven);
        eightButton = findViewById(R.id.bt_eight);
        nineButton = findViewById(R.id.bt_nine);
        tenButton = findViewById(R.id.bt_ten);
        elevenButton = findViewById(R.id.bt_eleven);
        twelveButton = findViewById(R.id.bt_twelve);
        thirteenButton =findViewById(R.id.bt_thirteen);
        fourteenButton = findViewById(R.id.bt_fourteen);
        fifteenButton = findViewById(R.id.bt_fifteen);
        sixteenButton = findViewById(R.id.bt_sixteen);
        Onclick onclick = new Onclick();
        twoButton.setOnClickListener(onclick);
        threeButton.setOnClickListener(onclick);
        fourButton.setOnClickListener(onclick);
        fiveButton.setOnClickListener(onclick);
        sevenButton.setOnClickListener(onclick);
        eightButton.setOnClickListener(onclick);
        nineButton.setOnClickListener(onclick);
        tenButton.setOnClickListener(onclick);
        elevenButton.setOnClickListener(onclick);
        twelveButton.setOnClickListener(onclick);
        thirteenButton.setOnClickListener(onclick);
        fourteenButton.setOnClickListener(onclick);
        fifteenButton.setOnClickListener(onclick);
        sixteenButton.setOnClickListener(onclick);
        //Meau与ContextMenu在与控件绑定
        //默认长按出来
        registerForContextMenu(tvtest);
        //请求SD卡修改权限
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

    }
    class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent =null;
            switch (v.getId()){
                case R.id.bt_two:
                    intent = new Intent(MainActivity.this,TwoMainActivity.class);
                    break;
                    case R.id.bt_three:
                        intent = new Intent(MainActivity.this,ThreeMainActivity.class);
                    break;
                case R.id.bt_four:
                    intent = new Intent(MainActivity.this,FourMainActivity.class);
                    break;
                case R.id.bt_five:
                    intent = new Intent(MainActivity.this,FiveMainActivity.class);
                    break;
                case R.id.bt_seven:
                    intent = new Intent(MainActivity.this,SevenMainActivity.class);
                    break;
                case R.id.bt_eight:
                    intent = new Intent(MainActivity.this,EightMainActivity.class);
                    break;
                case R.id.bt_nine:
                    intent = new Intent(MainActivity.this,NineMainActivity.class);
                    break;
                case R.id.bt_ten:
                    intent = new Intent(MainActivity.this, TenMainActivity.class);
                    break;
                case R.id.bt_eleven:
                    intent = new Intent(MainActivity.this, ElevenMainActivity.class);
                    break;
                case R.id.bt_twelve:
                    intent = new Intent(MainActivity.this, TwelveMainActivity.class);
                    break;
                case R.id.bt_thirteen:
                    intent = new Intent(MainActivity.this, ThirteenMainActivity.class);
                    break;
                case R.id.bt_fourteen:
                    intent = new Intent(MainActivity.this, FourteenMainActivity.class);
                    break;
                case R.id.bt_fifteen:
                    intent = new Intent(MainActivity.this, FifteenMainActivity.class);
                    break;
                case R.id.bt_sixteen:
                    intent = new Intent(MainActivity.this, SixteenMainActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
   //Activity绑定menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test,menu);
        return true;
    }
    //设置点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent =null;
        switch (item.getItemId()){
            case R.id.two:
                intent = new Intent(MainActivity.this,TwoMainActivity.class);
                break;
            case R.id.three:
                intent = new Intent(MainActivity.this,ThreeMainActivity.class);
                break;
            case R.id.four:
                intent = new Intent(MainActivity.this,FourMainActivity.class);
                break;
            case R.id.five:
                intent = new Intent(MainActivity.this,FiveMainActivity.class);
                break;
            case R.id.seven:
                intent = new Intent(MainActivity.this,SevenMainActivity.class);
                break;
            case R.id.eight:
                intent = new Intent(MainActivity.this,EightMainActivity.class);
                break;
            case R.id.nine:
                intent = new Intent(MainActivity.this,NineMainActivity.class);
                break;
            case R.id.ten:
                intent = new Intent(MainActivity.this,TenMainActivity.class);
                break;
            case R.id.eleven:
                intent = new Intent(MainActivity.this,ElevenMainActivity.class);
                break;
            case R.id.twelve:
                intent = new Intent(MainActivity.this,TwelveMainActivity.class);
                break;
            case R.id.thirteen:
                intent = new Intent(MainActivity.this,ThirteenMainActivity.class);
                break;
        }
        startActivity(intent);
        return true;
    }
//控件与Meau
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.tv_1:
                getMenuInflater().inflate(R.menu.test,menu);
                break;
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent =null;
        switch (item.getItemId()){
            case R.id.two:
                intent = new Intent(MainActivity.this,TwoMainActivity.class);
                break;
            case R.id.three:
                intent = new Intent(MainActivity.this,ThreeMainActivity.class);
                break;
            case R.id.four:
                intent = new Intent(MainActivity.this,FourMainActivity.class);
                break;
            case R.id.five:
                intent = new Intent(MainActivity.this,FiveMainActivity.class);
                break;
            case R.id.seven:
                intent = new Intent(MainActivity.this,SevenMainActivity.class);
                break;
            case R.id.eight:
                intent = new Intent(MainActivity.this,EightMainActivity.class);
                break;
            case R.id.nine:
                intent = new Intent(MainActivity.this,NineMainActivity.class);
                break;
            case R.id.ten:
                intent = new Intent(MainActivity.this,TenMainActivity.class);
                break;
            case R.id.eleven:
                intent = new Intent(MainActivity.this,ElevenMainActivity.class);
                break;
            case R.id.twelve:
                intent = new Intent(MainActivity.this,TwelveMainActivity.class);
                break;
            case R.id.thirteen:
                intent = new Intent(MainActivity.this,ThirteenMainActivity.class);
                break;
        }
        startActivity(intent);
        return true;
    }
}
