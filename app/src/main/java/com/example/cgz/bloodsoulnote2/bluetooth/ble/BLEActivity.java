package com.example.cgz.bloodsoulnote2.bluetooth.ble;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.install.AppInstallActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.functions.Consumer;

public class BLEActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "BLE";
    private BluetoothAdapter mAdapter;
    private ArrayAdapter<String> mArrayAdapter;
    private Set<BluetoothDevice> mDevices = new HashSet<>();
    private List<BluetoothDevice> mRealDevices = new ArrayList<>();
    private BluetoothGatt mBluetoothGatt = null;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mAdapter.stopLeScan(mLeScanCallback);
                    for (BluetoothDevice device : mDevices) {
                        mArrayAdapter.add(device.getName() + " / " + device.getAddress());
                    }
                    mRealDevices.clear();
                    mRealDevices.addAll(mDevices);
                    Log.i("BLE", "mDevices.size " + mDevices.size());
                    break;
            }
        }
    };

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);
        init();
        checkBle();

        BluetoothManager manager = (BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE);
        mAdapter = manager.getAdapter();
//        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    }

    private void init() {
        ListView listView = findViewById(R.id.listview);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mArrayAdapter);
        listView.setOnItemClickListener(this);
    }

    private void checkBle() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "设备不支持蓝牙4.0", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // 有些机型不管是否申请权限，都不弹框，只有调用某个方法时，才弹框
    public void requestPermissions(View view) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    Toast.makeText(BLEActivity.this, "accept", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BLEActivity.this, "deny", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    if (permission.granted) {
//        // `permission.name` is granted !
//        Toast.makeText(BLEActivity.this, "is granted !", Toast.LENGTH_SHORT).show();
//    } else if (permission.shouldShowRequestPermissionRationale) {
//        // Denied permission without ask never again
//        Toast.makeText(BLEActivity.this, "Denied permission without ask never again", Toast.LENGTH_SHORT).show();
//    } else {
//        // Denied permission with ask never again
//        // Need to go to the settings
//        Toast.makeText(BLEActivity.this, "Need to go to the settings", Toast.LENGTH_SHORT).show();
//    }

//    if (aBoolean) {
//        Toast.makeText(BLEActivity.this, "accept", Toast.LENGTH_SHORT).show();
//    } else {
//        Toast.makeText(BLEActivity.this, "deny", Toast.LENGTH_SHORT).show();
//    }

    // 必须要等待蓝牙开启了，才能正常使用扫描功能，无法同步执行，可以考虑广播的方式
    public void openBle(View view) {
        checkBleToggle();
    }

    @SuppressLint("NewApi")
    public void connect(View view) {
        mAdapter.startLeScan(mLeScanCallback);
        mHandler.sendEmptyMessageDelayed(0, 5000);
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            Log.i("BLE", "device : " + device.toString());
            mDevices.add(device);
        }
    };

    private boolean checkBleToggle() {
        if (isBleOpen()) {
            return true;
        }
        boolean enable = openBLE();
//        sysOpenBLE(this, 101);
        return enable;
    }

    // 判断蓝牙是否是开启的
    public boolean isBleOpen() {
        if (mAdapter == null) {
            return false;
        }
        boolean enabled = mAdapter.isEnabled();
        Log.i("BLE", "isBleOpen " + enabled);
        return enabled;
    }

    // 自动打开蓝牙
    public boolean openBLE() {
        if (mAdapter == null) {
            return false;
        }
        return mAdapter.enable();
    }

    // 系统设置手动打开蓝牙
    public void sysOpenBLE(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("NewApi")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = mRealDevices.get(position);
        device.connectGatt(this, false, mBluetoothGattCallback);
    }

    private BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {

        /**
         * 此方法在成功连接到远程设备时调用，不调用此方法，无法与远程设备进行后续的通信。但是这个方法是异步操作
         */
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.i(TAG, "Connected to GATT server.");
                gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.i(TAG, "Disconnected from GATT server.");
            }
        }

        /**
         * 在回调函数onServicesDiscovered中得到status，通过判断status是否等于BluetoothGatt.GATT_SUCCESS来判断查找Service是否成功
         */
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                //服务发现成功
                mBluetoothGatt = gatt;
            } else {
                //服务发现失败
            }
        }
    };

    // 断开连接
    public void disConnection() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.disconnect();
    }

    // 关闭 gatt
    public void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.close();
        mBluetoothGatt = null;
    }
}
