package com.example.cgz.bloodsoulnote2.async.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class HandlerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
    }

    private void test() {
        Handler handler = new Handler();

//        handler.sendMessage()
//        handler.sendMessageDelayed();
//        handler.sendEmptyMessage();
//        handler.sendEmptyMessageDelayed();
//        handler.sendMessageAtTime();
//
//        handler.post();
//        handler.postDelayed();
//        handler.postAtTime();
//
//        handler.obtainMessage();
//        handler.dispatchMessage();
//        handler.removeMessages();

        Message.obtain();


        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                return false;
            }
        });
    }
}
