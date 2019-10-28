package com.example.huhaichang.learn3.two;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.huhaichang.learn3.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_web_view);

        wv = findViewById(R.id.wv_1);
        //加载本地Html文件
        //wv.loadUrl("file:///android_asset/hello.html");
        //加载网络UrL
        wv.getSettings().setJavaScriptEnabled(true);
        //防止跳转外部浏览器请求
        wv.setWebViewClient(new MyWebViewClient());
        //可以加载时间调显示 改主题
        wv.setWebChromeClient(new MyWebChromeClient());
        wv.loadUrl("http://m.mijisou.com");
    }

    class MyWebViewClient extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //页面加载开始时 代码写这里
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //页面加载结束时
            super.onPageFinished(view, url);
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            title = "秘迹搜索";
            setTitle(title);
        }
    }
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
                wv.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }

}