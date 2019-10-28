package com.example.huhaichang.learn3.four.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.four.ContainerActivity;

/**
 * Created by huhaichang on 2019/6/22.
 */

public class BFragment extends Fragment {
    private TextView mtv_title;
    @Nullable
    //设置布局文件
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mtv_title = view.findViewById(R.id.tv_title);
        ((ContainerActivity) getActivity()).setData(1);

    }

}
