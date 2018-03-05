package com.example.cgz.bloodsoulnote2.service.start;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class StartService extends Service {

    private static final String TAG = "StartService";

    @Override
    public void onCreate() {
        log("onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("onBind");
        return null;
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
