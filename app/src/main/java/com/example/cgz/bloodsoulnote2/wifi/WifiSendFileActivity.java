package com.example.cgz.bloodsoulnote2.wifi;

import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.wifi.send.SendTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class WifiSendFileActivity extends Wifip2pBaseActivity {

    private AlertDialog mDialog;
    private ArrayList<String> mListDeviceName = new ArrayList<>();
    private ArrayList<WifiP2pDevice> mListDevice = new ArrayList<>();
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_send_file);

        mListView = findViewById(R.id.peer);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mListDeviceName);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                connect(position);
            }
        });
    }

    private void connect(int position) {
        WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
        WifiP2pDevice wifiP2pDevice = mListDevice.get(position);
        if (wifiP2pDevice != null) {
            wifiP2pConfig.deviceAddress = wifiP2pDevice.deviceAddress;
            wifiP2pConfig.wps.setup = WpsInfo.PBC;
            mWifiP2pManager.connect(mChannel, wifiP2pConfig, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    Log.i(TAG, "连接成功");
                }

                @Override
                public void onFailure(int reason) {
                    Log.i(TAG, "连接失败");
                }
            });
        }
    }

    public void discover(View view) {
        showProgressBar();

        mWifiP2pManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                // WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION 广播，此时就可以调用 requestPeers 方法获取设备列表信息
                Log.e(TAG, "搜索设备成功");
                //进度条消失
                hideDialog();
            }

            @Override
            public void onFailure(int reason) {
                Log.e(TAG, "搜索设备失败");
                //进度条消失
                hideDialog();
            }
        });
    }

    private void hideDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onPeersInfo(Collection<WifiP2pDevice> wifiP2pDeviceList) {
        super.onPeersInfo(wifiP2pDeviceList);

        for (WifiP2pDevice device : wifiP2pDeviceList) {
            if (!mListDeviceName.contains(device.deviceName) && !mListDevice.contains(device)) {
                mListDeviceName.add("设备：" + device.deviceName + "----" + device.deviceAddress);
                mListDevice.add(device);
            }
        }

        if (mListDeviceName.size() == 0) {
            return;
        }

        mAdapter.clear();
        mAdapter.addAll(mListDeviceName);
        mAdapter.notifyDataSetChanged();
    }

    private void showProgressBar() {
        mDialog = new AlertDialog.Builder(this, R.style.Transparent).create();
        mDialog.show();
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.loading_progressbar);
    }

    public void choseFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = FileUtils.getAbsolutePath(this, uri);
                    Log.i(TAG, "uri:" + uri + " path:" + path);
                    if (path != null) {
                        File file = new File(path);
                        if (!file.exists() || mWifiP2pInfo == null) {
                            Toast.makeText(WifiSendFileActivity.this,"文件路径找不到",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String md5 = Md5Util.getMd5(file);
                        FileBean fileBean = new FileBean(file.getPath(), file.length(), md5);
                        String hostAddress = mWifiP2pInfo.groupOwnerAddress.getHostAddress();

                        // TODO: 2019/2/16
                        new SendTask(WifiSendFileActivity.this, fileBean).execute(hostAddress);
                    }
                }
            }
        }
    }
}
