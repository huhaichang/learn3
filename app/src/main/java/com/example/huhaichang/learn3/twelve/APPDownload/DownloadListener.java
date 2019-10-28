package com.example.huhaichang.learn3.twelve.APPDownload;

/**
 * Created by huhaichang on 2019/8/7.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
