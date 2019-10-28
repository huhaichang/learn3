package com.example.huhaichang.learn3.four.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.four.ContainerActivity;

/**
 * Created by huhaichang on 2019/6/22.
 */

public class AFragment extends Fragment {
    private TextView mtv_title;
    private  Button mbtchange,mbtsend;
    private IOnMessageOnclick listener;
    public static AFragment repalceContent(String title){
        AFragment aFragment =new AFragment();
        Bundle bundle =new Bundle();
        bundle.putString("a",title);
        aFragment.setArguments(bundle);
        return  aFragment;
    }
    @Nullable
    //设置布局文件 和开始加载视图时执行
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }
    //可以对视图进行操作
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mtv_title = view.findViewById(R.id.tv_title);
        mbtchange = view.findViewById(R.id.bt_change);
        mbtsend = view.findViewById(R.id.bt_send);
        ((ContainerActivity)getActivity()).setData(0);
        if(getArguments()!=null){
            mtv_title.setText(getArguments().getString("a"));
        }

        mbtchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtv_title.setText("变换后的AFragment");
            }
        });
        mbtsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //activity的对象获取
              // ((ContainerActivity)getActivity()).setData("你好");
               listener.onClick("你好啊");
            }
        });
    }

    //定义一个接口
    public interface IOnMessageOnclick {
        void onClick(String text);
    }

    //当Activity与Fragment重新绑定时触发
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //把Activity绑定到接口
            listener = (IOnMessageOnclick) context;
        }catch(ClassCastException e){
           throw new ClassCastException("Activity必须实现IOnMessageOnclick接口");
        }
    }


    //当Activity与Fragment脱离绑定时
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("1","2");
        ((ContainerActivity)getActivity()).setData(0);
    }
    //当Fragment销毁时一般用于取消异步
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("1","3");
        ((ContainerActivity)getActivity()).setData(0);
    }

}
