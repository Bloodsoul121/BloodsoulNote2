package com.example.cgz.bloodsoulnote2.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("BroadcastReceiver");
        Log.i("MyBroadcastReceiver", "onReceive --> " + msg);

        // 修改数据
        Bundle extras = new Bundle();
        extras.putString("BroadcastReceiver", "hehe");
        setResultExtras(extras);

        setResultData("heihei");

        // 拦截广播
//        abortBroadcast();
    }

}
