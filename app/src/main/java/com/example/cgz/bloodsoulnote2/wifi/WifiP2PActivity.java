package com.example.cgz.bloodsoulnote2.wifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.cgz.bloodsoulnote2.R;

public class WifiP2PActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_p2_p);
    }

    public void sendFile(View view) {
        startActivity(new Intent(this, WifiSendFileActivity.class));
    }

    public void receiveFile(View view) {

    }

}
