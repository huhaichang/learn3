package com.example.huhaichang.learn3.two;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.huhaichang.learn3.R;


public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.rv_news);

        webView = findViewById(R.id.wv_news);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://m.baidu.com");

        //RecycleView相应的东西
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //放置下划线
        recyclerView.addItemDecoration(new MyDecoration2());

        webView.setWebChromeClient(new MyWebChromeClient());

        recyclerView.setAdapter(new NewsAdapter(NewsActivity.this, new NewsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                String s[] = {"百度","体育","军事","头条","社会","国内","国际","娱乐","科技","财经","时尚"};
                Toast.makeText(NewsActivity.this, s[pos], Toast.LENGTH_SHORT).show();
                switch (pos){
                    case 0:webView.loadUrl("http://m.baidu.com");break;
                    case 1:webView.loadUrl("http://m.hupu.com");break;
                    case 2:webView.loadUrl("http://3g.china.com/html/mili.html");break;
                    case 3:webView.loadUrl("https://t.cj.sina.cn/?vt=4&pos=18");break;
                    case 4:webView.loadUrl("http://app.myzaker.com/index.php?app_id=14");break;
                    case 5:webView.loadUrl("https://3g.163.com/touch/news/subchannel/domestic/#adaptation=pc");break;
                    case 6:webView.loadUrl("https://3g.163.com/touch/news/subchannel/international/#adaptation=pc");break;
                    case 7:webView.loadUrl("https://ient.ifeng.com");break;
                    case 8:webView.loadUrl("https://xw.qq.com/m/tech/index.htm");break;
                    case 9:webView.loadUrl("https://finance.sina.cn/?from=wap");break;
                    case 10:webView.loadUrl("http://m.ladymax.cn/");break;
                }

            }
        }));

    }
    class MyDecoration2 extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight2), getResources().getDimensionPixelOffset(R.dimen.dividerHeight2));
        }

    }
    class MyWebViewClient extends WebViewClient{
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
            //让标题为原网页标题
            setTitle(title);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
