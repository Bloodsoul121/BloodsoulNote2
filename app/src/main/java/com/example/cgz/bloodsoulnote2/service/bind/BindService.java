package com.example.cgz.bloodsoulnote2.service.bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BindService extends Service {

    private static final String TAG = "BindService";

    public class MyBinder extends Binder {

        public void startDownload() {
            log("startDownload() executed");
            // 执行具体的下载任务
        }

    }

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        log("onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        log("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        log("onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        log("onDestroy");
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }

}
