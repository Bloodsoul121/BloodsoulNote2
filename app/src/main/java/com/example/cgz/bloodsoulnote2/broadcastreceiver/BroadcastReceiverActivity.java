package com.example.cgz.bloodsoulnote2.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class BroadcastReceiverActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        registerReceiver();
        registerReceiver2();
    }

    private void registerReceiver2() {
        SecondBroadcastReceiver broadcastReceiver = new SecondBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.cgz.bloodsoulnote2.broadcastReceiver");
        intentFilter.setPriority(99);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void registerReceiver() {
        MyBroadcastReceiver broadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.cgz.bloodsoulnote2.broadcastReceiver");
        intentFilter.setPriority(100);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    public void sendBroadcastReceiver(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.cgz.bloodsoulnote2.broadcastReceiver");
        intent.putExtra("BroadcastReceiver", "haha");
//        sendBroadcast(intent);  // 普通广播
        sendOrderedBroadcast(intent, null);  // 有序广播
    }
}
