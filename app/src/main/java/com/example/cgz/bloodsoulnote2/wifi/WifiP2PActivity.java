package com.example.cgz.bloodsoulnote2.wifi;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class WifiP2PActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_p2_p);
        requestPermissions();
    }

    public void sendFile(View view) {
        startActivity(new Intent(this, WifiSendFileActivity.class));
    }

    public void receiveFile(View view) {
        startActivity(new Intent(this, WifiReceiveFileActivity.class));
    }

    public void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                                )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Toast.makeText(WifiP2PActivity.this, "accept", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(WifiP2PActivity.this, "deny", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
