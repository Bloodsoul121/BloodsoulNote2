package com.example.cgz.bloodsoulnote2.service.bind;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService() {
        super("IntentService-thread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        log("onHandleIntent");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        log("onCreate");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        log("onStart");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        log("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("onBind");
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        log("onDestroy");
        super.onDestroy();
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }

    /*
     * 03-02 16:49:18.543 14475-14475/com.example.cgz.bloodsoulnote2 I/MyIntentService: onCreate
     * 03-02 16:49:18.543 14475-14475/com.example.cgz.bloodsoulnote2 I/MyIntentService: onStartCommand
     * 03-02 16:49:18.543 14475-14475/com.example.cgz.bloodsoulnote2 I/MyIntentService: onStart
     * 03-02 16:49:18.543 14475-16307/com.example.cgz.bloodsoulnote2 I/MyIntentService: onHandleIntent
     * 03-02 16:49:18.545 14475-14475/com.example.cgz.bloodsoulnote2 I/MyIntentService: onDestroy
     */

}
