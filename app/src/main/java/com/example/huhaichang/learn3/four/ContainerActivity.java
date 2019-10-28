package com.example.huhaichang.learn3.four;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.four.fragment.AFragment;
import com.example.huhaichang.learn3.four.fragment.BFragment;
import com.example.huhaichang.learn3.widget.ToastUtil;

//
public class ContainerActivity extends AppCompatActivity implements AFragment.IOnMessageOnclick{
  private Fragment aFragment,bFragment;
  private Button mbtChange;
  private TextView mtvAccept;
  private FrameLayout frameLayout;
  private Float mStartX,mSwipeX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        mtvAccept = findViewById(R.id.tv_accept);
        frameLayout = findViewById(R.id.fl_container);
        //实例化AFragment
        //aFragment = new AFragment();
       aFragment = AFragment.repalceContent("替换WWWAA内容");
        //1.把AFragment添加到Activity中,记得调用commitAllowingStateLoss()
        // 2.replace(R.id.fl_container,bFragment)还可以传入标识 通过getFragmentManager().findFragmentTag（）来找到;
        //将布局文件加载到帧布局里面
       getFragmentManager().beginTransaction().add(R.id.fl_container,aFragment,"a").commitAllowingStateLoss();

        mbtChange = findViewById(R.id.bt_change);
        mbtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bFragment==null){
                    bFragment = new BFragment();
                }
                if(!bFragment.isAdded()) {

                // 1.如果要按返回键后 返回包括Fragment的变换则加入    addToBackStack(null)
                // 2.getFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment,"a").addToBackStack(null).commitAllowingStateLoss();
                   getFragmentManager().beginTransaction().hide(aFragment).add(R.id.fl_container,bFragment,"b").addToBackStack(null).commitAllowingStateLoss();

            }
            else if(aFragment.isHidden()){
                    getFragmentManager().beginTransaction().show(aFragment).replace(R.id.fl_container,aFragment).addToBackStack(null).commitAllowingStateLoss();
                }
            }
        });
            //对按下和移动后的坐标记性记录  在抬起手时执行
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mStartX=event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mSwipeX =event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        //左滑
                        if(mStartX-mSwipeX>0&&Math.abs(mStartX-mSwipeX)>200) {
                            if (bFragment == null) {
                                bFragment = new BFragment();
                               // getFragmentManager().beginTransaction().hide(aFragment).add(R.id.fl_container,bFragment,"b").addToBackStack(null).commitAllowingStateLoss();
                            }
                            if(!bFragment.isAdded()) {
                                getFragmentManager().beginTransaction().hide(aFragment).add(R.id.fl_container,bFragment,"b").addToBackStack(null).commitAllowingStateLoss();

                            }
                        }
                        if(mStartX-mSwipeX<0&&Math.abs(mStartX-mSwipeX)>200){
                            if(aFragment.isHidden()){
                                getFragmentManager().beginTransaction().show(aFragment).replace(R.id.fl_container,aFragment).addToBackStack(null).commitAllowingStateLoss();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
   //方法1.
        public void setData(int b){

         }
    //2.通过接口来设置
        @Override
        public void onClick(String text) {
        mtvAccept.setText(text);

        }

}





