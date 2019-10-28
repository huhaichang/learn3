package com.example.huhaichang.learn3.four;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huhaichang.learn3.R;

/**
 * Created by huhaichang on 2019/7/23.
 */

public class NewsContentFragment extends Fragment {
    private TextView mTvContent;
    private View view;
    public static NewsContentFragment repalceContent(String content){
        NewsContentFragment newsContentFragment = new NewsContentFragment();
        Bundle bundle =new Bundle();
        bundle.putString("a",content);
        newsContentFragment.setArguments(bundle);
        return  newsContentFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.layout_news_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvContent = view.findViewById(R.id.tv_newsContent);
        mTvContent.setText(getArguments().getString("a"));

    }
}
