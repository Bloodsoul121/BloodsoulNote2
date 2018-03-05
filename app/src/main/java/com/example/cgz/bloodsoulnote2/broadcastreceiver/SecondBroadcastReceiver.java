package com.example.cgz.bloodsoulnote2.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("BroadcastReceiver");
        Log.i("SecondBroadcastReceiver", "onReceive --> " + msg);

        Bundle bundle = getResultExtras(true);
        String bundleMsg = bundle.getString("BroadcastReceiver", "");
        Log.i("SecondBroadcastReceiver", "onReceive --> " + bundleMsg);

        String resultData = getResultData();
        Log.i("SecondBroadcastReceiver", "onReceive --> " + resultData);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("SecondBroadcastReceiver", "Thread");
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("SecondBroadcastReceiver", "Thread");
            }
        }).start();
    }
}
