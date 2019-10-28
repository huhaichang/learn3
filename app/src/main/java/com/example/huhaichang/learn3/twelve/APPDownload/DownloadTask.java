package com.example.huhaichang.learn3.twelve.APPDownload;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huhaichang on 2019/8/6.
 */
                                         //传入地址   进度    返回结果
public class DownloadTask extends AsyncTask<String,Integer,Integer> {
    public static final int TYPE_SUCCESS =0; //下载结果1
    public static final int TYPE_FAILED =1; //下载结果2
    public static final int TYPE_PAUSED = 2;  //下载结果3
    public static final int TYPE_CANCELED =3; //下载结果4

    private boolean isCanceled = false;  //下载不是成功就失败  设置中途可执行操作默认为false
    private boolean isPaused = false;
    private int lastProgress=0;    //变换的进度Integer[0]>lastProgress，lastProgress=Integer[0] 表示Integer[0]进度在增加 表示在下载
    private DownloadListener listener;

    public  DownloadTask(DownloadListener listener){
        this.listener = listener;
    }

    //后台运行指令
    @Override
    protected Integer doInBackground(String... strings) {
        InputStream inputStream = null;
        RandomAccessFile savedFile = null;
        File file =null;
        long downloadlength = 0; //记录下载长度 直接是file.length
        //获取下载的APP地址
        String downloadUrl = strings[0];
        //设置下载到XXX的位置
        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        file = new File(directory+fileName);
        if(file.exists()) {
            downloadlength = file.length();
        }
        try {
            long contentLength = getContentLength(downloadUrl); //获取总下载文件的长度 用来比较
            //得先判断有没有获取到总下载长度
            if(contentLength == 0){
                return TYPE_FAILED;
            }else if(contentLength == downloadlength){
                //已经下载完了 就不用下载了
                return TYPE_SUCCESS;
            }
            //当暂停时 得获取已下载的长度 并从已下载长度后面开始下载
            //通过okHttp获取已经下载的长度total
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //当断点下载时 指定从哪个字节开始下载
                    .addHeader("RANGE","byte="+downloadlength+"-") //告诉服务器从哪里开始下载 返回的结果就是未下载的长度
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if(response!=null){
                //通过输入流获取长度   把网络上的数据不断的读入本地数据 （就是正在下载的意思）
                inputStream = response.body().byteStream();
                savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downloadlength);
                byte[] b = new byte[1024];
                int total = 0;
                int len = 0 ;
                //条件完毕  开始或继续下载（开始读） 3种情况
                while ((len=inputStream.read(b))!=-1){
                    int progress=0;
                    if(isCanceled){
                        return  TYPE_CANCELED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else{
                        total +=len;
                        savedFile.write(b,0,len);
                        //计算下载的百分比
                         progress = (int) ((total+downloadlength)*100/contentLength);
                        //传入更新的progressUpdate
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
                try {
                    if(inputStream!=null){
                       inputStream.close();
                    }
                    if(savedFile!=null){
                        savedFile.close();
                    }
                    //如果取消的话把文件删了
                    if(isCanceled&&file!=null){
                        file.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return null;
    }
    //显示更新(获取进度)
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        //如果进度在增加
        if(progress>lastProgress){
            //把进度给接口
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }
    //中途用户的操作
    public void pauseDownload(){
        isPaused = true;
    }
    public  void cancelDownload(){
        isCanceled =true;
    }
    //通知结果给接口
    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            default:
                break;
        }
    }
    //获取下载文件的总大小（长度）
    private long getContentLength(String downloadUrl) throws IOException {
        //通过okHttp的get请求返回文件长度
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if(response != null && response.isSuccessful()){
            //获取文件长度
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        //获取失败表示下载失败
        return 0;
    }
}
