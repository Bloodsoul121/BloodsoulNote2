package com.example.cgz.bloodsoulnote2.view.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.view.custom.setting.SettingActivity;

public class CustomViewActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    @Override
    protected void initData() {
        mDatas.add("设置界面");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(SettingActivity.class);
                break;
        }
    }
}
