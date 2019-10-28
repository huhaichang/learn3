package com.example.huhaichang.learn3.three;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huhaichang.learn3.R;

public class ThreeMainActivity extends AppCompatActivity {
    private Button mtoast;
    private Button mAlertDialog;
    private Button mProcess;
    private Button mdialog;
    private Button mpupopwindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_mian);
        mtoast = findViewById(R.id.bt_1);
        mAlertDialog =findViewById(R.id.bt_2);
        mProcess = findViewById(R.id.bt_3);
        mdialog = findViewById(R.id.bt_4);
        mpupopwindow = findViewById(R.id.bt_5);

        OnClick onClick =new OnClick();
        mAlertDialog.setOnClickListener(onClick);
        mtoast.setOnClickListener(onClick);
        mProcess.setOnClickListener(onClick);
        mdialog.setOnClickListener(onClick);
        mpupopwindow.setOnClickListener(onClick);

    }
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.bt_2:
                            intent = new Intent(ThreeMainActivity.this, AlertDialogActivity.class);
                    break;
                case R.id.bt_1:
                   intent = new Intent(ThreeMainActivity.this,ToastActivity.class);
                    break;
                case R.id.bt_3:
                    intent = new Intent(ThreeMainActivity.this,ProgressActivity.class);
                    break;
                case R.id.bt_4:
                    intent = new Intent(ThreeMainActivity.this,CustomDialogActivity.class);
                    break;
                case R.id.bt_5:
                    intent = new Intent(ThreeMainActivity.this,PopupWindowActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
