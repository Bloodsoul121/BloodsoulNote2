package com.example.cgz.bloodsoulnote2.thread.handlethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cgz on 18-3-2.
 */

public class DownloadThread extends HandlerThread implements Handler.Callback {

    private final String KEY_URL = "url";
    public static final int TYPE_START = 1;
    public static final int TYPE_FINISHED = 2;

    private Handler mUIHandler;
    private List<String> mDownloadUrlList;

    public DownloadThread(String name) {
        super(name);
    }

    public DownloadThread(String name, int priority) {
        super(name, priority);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();

        Handler workerHandler = new Handler(getLooper(), this);
        if (mUIHandler == null) {
            throw new IllegalArgumentException("Not set UIHandler!");
        }

        // 将接收到的任务消息挨个添加到消息队列中
        for (String url : mDownloadUrlList) {
            Message message = workerHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_URL, url);
            message.setData(bundle);
            workerHandler.sendMessage(message);
        }
    }

    public void setDownloadUrls(String... urls) {
        mDownloadUrlList = Arrays.asList(urls);
    }

    public DownloadThread setUIHandle(Handler handler) {
        this.mUIHandler = handler;
        return this;
    }

    public Handler getUIHandler() {
        return this.mUIHandler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg == null || msg.getData() == null) {
            return false;
        }

        String url = msg.getData().getString(KEY_URL);

        Message startMsg = mUIHandler.obtainMessage(TYPE_START, "\n 开始下载 @" + System.currentTimeMillis() + "\n" + url);
        mUIHandler.sendMessage(startMsg);

        // 模拟下载
        SystemClock.sleep(2000);

        Message finishMsg = mUIHandler.obtainMessage(TYPE_FINISHED, "\n 下载完成 @" + System.currentTimeMillis() + "\n" + url);
        mUIHandler.sendMessage(finishMsg);

        return true;  // 自己处理
    }

    @Override
    public boolean quitSafely() {
        mUIHandler = null;
        return super.quitSafely();
    }
}
