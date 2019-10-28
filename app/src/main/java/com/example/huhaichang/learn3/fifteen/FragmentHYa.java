package com.example.huhaichang.learn3.fifteen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

/**
 * Created by huhaichang on 2019/8/24.
 */

public class FragmentHYa extends Fragment{
    private TextView mTVcontent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_hya,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTVcontent =view.findViewById(R.id.tv_content);
        StringBuilder sb =new StringBuilder();
        for(int i =0;i<500;i++){
            sb.append("fruitName ");
        }
        mTVcontent.setText(sb.toString());

    }
}
