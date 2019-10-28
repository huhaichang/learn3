package com.example.huhaichang.learn3.three;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.huhaichang.learn3.R;



public class ToastActivity extends AppCompatActivity {
    private Button toast1,toast2,toast3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        toast1=findViewById(R.id.bt_toast_1);
        toast2=findViewById(R.id.bt_toast_2);
        toast3=findViewById(R.id.bt_toast_3);
        OnClick onClick = new OnClick();
       toast1.setOnClickListener(onClick);
        toast2.setOnClickListener(onClick);
        toast3.setOnClickListener(onClick);
    }
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_toast_1:
                    Toast.makeText(ToastActivity.this,"默认位置",Toast.LENGTH_SHORT).show();

                break;
                case R.id.bt_toast_2:
                    Toast toastCenter = Toast.makeText(ToastActivity.this,"改变位置显示",Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER,-200,5);
                    toastCenter.show();


                    break;
                case R.id.bt_toast_3:
                    Toast toastCustom = new Toast(ToastActivity.this);
                    //素质4连
                    LayoutInflater inflater =LayoutInflater.from(ToastActivity.this);
                    View view=inflater.inflate(R.layout.layout_toast,null);
                    ImageView imageView = view.findViewById(R.id.iv_toast);
                    TextView textView =view.findViewById(R.id.tv_toast);

                    imageView.setImageResource(R.drawable.goodbay);
                    textView.setText("自定义Toast");
                    toastCustom.setView(view);
                    //显示1秒
                    toastCustom.setDuration(Toast.LENGTH_SHORT);
                    toastCustom.show();
                    break;
            }
        }
    }
}
