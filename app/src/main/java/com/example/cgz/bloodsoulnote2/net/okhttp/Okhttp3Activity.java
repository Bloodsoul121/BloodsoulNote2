package com.example.cgz.bloodsoulnote2.net.okhttp;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Okhttp3Activity extends BaseActivity {

    private static final String TAG = "Okhttp3Activity";

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
    }

    public void clickBtn1(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("https://www.baidu.com")
                .method("GET", null)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log(TAG, "onFailure --> " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.cacheResponse() != null) {
                    log(TAG, response.cacheResponse().toString());
                } else {
                    log(TAG, response.body().string());
                    log(TAG, response.networkResponse().toString());
                }
            }
        });
    }

    public void clickBtn2(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("size", "10")
                .build();

        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log(TAG, response.body().string());
            }
        });
    }

    public void clickBtn3(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();

        String path = Environment.getExternalStorageDirectory() + "/WebRtc_trace.txt";
        File file = new File(path);
//        File file = new File("/sdcard/WebRtc_trace.txt");
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, file);

        Request.Builder builder = new Request.Builder();
        Request request = builder.url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log(TAG, "onFailure --> " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log(TAG, response.body().string());
            }
        });
    }

    public void clickBtn4(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();

        String url = "http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg";

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream in = response.body().byteStream();
                FileOutputStream out = new FileOutputStream(new File("/sdcard/bbb.jpg"));
                byte[] arr = new byte[1028];
                int len;
                while ((len = in.read(arr)) != -1) {
                    out.write(arr, 0, len);
                }
                out.flush();
                out.close();
                log("下载成功");
            }
        });
    }

    public void clickBtn5(View view) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "wangshu")
                .addFormDataPart("image", "wangshu.jpg",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("/sdcard/wangshu.jpg")))
                .build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "...")
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, response.body().string());
            }
        });
    }

    public void clickBtn6(View view) {
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                .build();
    }

    public void clickBtn7(View view) {
//        call.cancel();
    }
}
