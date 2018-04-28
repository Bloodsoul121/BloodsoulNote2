package com.example.cgz.bloodsoulnote2.net.okhttp;

import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Okhttp2Activity extends BaseActivity {

    private static final String TAG = "Okhttp2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp2);
    }

    public void clickBtn1(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("https://www.baidu.com").build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Logger.i(TAG, response.body().string());
            }
        });
    }

    public void clickBtn2(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //创建请求Request
                final Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .build();
                Call call = mOkHttpClient.newCall(request);
                Response mResponse = null;
                try {
                    mResponse = call.execute();
                    if (mResponse.isSuccessful()) {
                        log(TAG, mResponse.body().string());
                    } else {
                        throw new IOException("Unexpected code " + mResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void clickBtn3(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                log(TAG, response.body().string());
            }
        });
    }

    public void clickBtn4(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        okHttpClient.setCache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
    }

    public void clickBtn5(View view) {
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .cacheControl(CacheControl.FORCE_NETWORK) // 用来表示请求会一直请求网络得到数据
                .build();
    }

    public void clickBtn6(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
    }
}
