package com.example.huhaichang.learn3.fifteen.AD;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;
import com.example.huhaichang.learn3.widget.ToastUtil;

/**
 * Created by huhaichang on 2019/8/20.
 */

@SuppressLint("ValidFragment")
public class ADFragment extends Fragment {
    private String context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_ad,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView mTv15 = view.findViewById(R.id.tv_title);
           final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.SRL_1);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mTv15.setText(context);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTv15.setText("文字");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }
    @SuppressLint("ValidFragment")
    public ADFragment(String context){
        super();
        this.context=context;

    }
}
