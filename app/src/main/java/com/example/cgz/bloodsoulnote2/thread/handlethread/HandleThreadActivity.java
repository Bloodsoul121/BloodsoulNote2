package com.example.cgz.bloodsoulnote2.thread.handlethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class HandleThreadActivity extends BaseActivity {

    private static final String TAG = "HandleThreadActivity";

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_thread);

        init();
    }

    private void init() {
        mHandlerThread = new HandlerThread("handle-thread");
        mHandlerThread.start();

        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                log(TAG, "handleMessage --> " + msg.what);
            }
        };

        // 主线程发送消息
        mHandler.sendEmptyMessage(1);

        // 子线程发送消息
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(2);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }
}
