package com.example.cgz.bloodsoulnote2.wifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Collection;

public abstract class Wifip2pBaseActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener, Wifip2pActionListener {

    protected static final String TAG = "wifi p2p";

    protected WifiP2pManager mWifiP2pManager;
    protected WifiP2pManager.Channel mChannel;
    protected Wifip2pReceiver mWifip2pReceiver;
    protected WifiP2pInfo mWifiP2pInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        unregisterReceiver(mWifip2pReceiver);
        mWifip2pReceiver = null;
    }


    private void init() {
        mWifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        if (mWifiP2pManager != null) {
            mChannel = mWifiP2pManager.initialize(this, getMainLooper(), this);
        }

        mWifip2pReceiver = new Wifip2pReceiver(mWifiP2pManager, mChannel, this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        registerReceiver(mWifip2pReceiver, intentFilter);
    }

    @Override
    public void onChannelDisconnected() {

    }

    @Override
    public void wifiP2pEnabled(boolean enabled) {
        Log.i(TAG, "wifi p2p 功能是否可用 " + enabled);
    }

    @Override
    public void onConnection(WifiP2pInfo wifiP2pInfo) {
        if (wifiP2pInfo != null) {
            mWifiP2pInfo = wifiP2pInfo;
            Log.i(TAG, "WifiP2pInfo:" + wifiP2pInfo);
        }
    }

    @Override
    public void onDisconnection() {
        Log.i(TAG,"连接断开");
    }

    @Override
    public void onDeviceInfo(WifiP2pDevice wifiP2pDevice) {
        Log.i(TAG,"设备信息：" + wifiP2pDevice.toString());
    }

    @Override
    public void onPeersInfo(Collection<WifiP2pDevice> wifiP2pDeviceList) {
        Log.i(TAG,"户群信息");
        for (WifiP2pDevice device : wifiP2pDeviceList) {
            Log.i(TAG, "连接的设备信息：" + device.deviceName + "----" + device.deviceAddress);
        }
    }
}
