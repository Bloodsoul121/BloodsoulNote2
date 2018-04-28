package com.example.cgz.bloodsoulnote2.bluetooth;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.bluetooth.bluedemo1.BlueDemo1Activity;

public class BlueToothActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
    }

    @Override
    protected void initData() {
        mDatas.add("蓝牙 api");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(BlueDemo1Activity.class);
                break;
        }
    }
}
