package com.example.cgz.bloodsoulnote2.wifi;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.wifi.receive.ReceiveSocket;
import com.example.cgz.bloodsoulnote2.wifi.receive.Wifip2pService;

import java.io.File;

public class WifiReceiveFileActivity extends Wifip2pBaseActivity implements ReceiveSocket.ProgressReceiveListener {

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Wifip2pService.MyBinder binder = (Wifip2pService.MyBinder) service;
            binder.initListener(WifiReceiveFileActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //服务断开重新绑定
            bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    };

    private Intent mIntent;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_receive_file);

        mIntent = new Intent(this, Wifip2pService.class);
        startService(mIntent);
        bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clear();
    }

    /**
     * 释放资源
     */
    private void clear() {
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
        }
        if (mIntent != null) {
            stopService(mIntent);
        }
    }

    public void createGroup(View view) {
        mWifiP2pManager.createGroup(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "创建群组成功");
                Toast.makeText(WifiReceiveFileActivity.this, "创建群组成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reason) {
                Log.i(TAG, "创建群组失败: " + reason);
                Toast.makeText(WifiReceiveFileActivity.this, "创建群组失败,请移除已有的组群或者连接同一WIFI重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteGroup(View view) {
        mWifiP2pManager.removeGroup(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "移除组群成功");
                Toast.makeText(WifiReceiveFileActivity.this, "移除组群成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reason) {
                Log.i(TAG, "移除组群失败");
                Toast.makeText(WifiReceiveFileActivity.this, "移除组群失败,请创建组群重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSatrt() {
        mProgressDialog = new ProgressDialog(this);
    }

    @Override
    public void onProgressChanged(File file, int progress) {
        Log.e(TAG, "接收进度：" + progress);
        mProgressDialog.setProgress(progress);
    }

    @Override
    public void onFinished(File file) {
        Log.e(TAG, "接收完成");
        mProgressDialog.dismiss();
        Toast.makeText(this, file.getName() + "接收完毕！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFaliure(File file) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(this, "接收失败，请重试！", Toast.LENGTH_SHORT).show();
    }
}
