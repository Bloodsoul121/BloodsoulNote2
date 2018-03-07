package com.example.cgz.bloodsoulnote2.thread.threadpool;

import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
    }

    private void test() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
//        service.schedule();
//        service.scheduleAtFixedRate();
    }

}
