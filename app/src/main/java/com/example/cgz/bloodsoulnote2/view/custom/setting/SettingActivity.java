package com.example.cgz.bloodsoulnote2.view.custom.setting;

import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity {

    private CustomSettingView mCustomSettingView;
    private SettingSwitchView mSwitchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        mCustomSettingView = (CustomSettingView) findViewById(R.id.custom_setting_view);
        mSwitchView = (SettingSwitchView) findViewById(R.id.setting_switch_view);
    }

    public void testFuction1(View view) {
        SettingListDialog dialog = new SettingListDialog(this);
        dialog.show();
    }

    public void testFuction2(View view) {
        CustomDialog dialog = new CustomDialog(this) {
            @Override
            public String getTitle() {
                return "title";
            }

            @Override
            public String getExtra() {
                return "extra";
            }

            @Override
            public List<String> getItems() {
                List<String> datas = new ArrayList<>();
                datas.add("aaa");
                datas.add("bbb");
                datas.add("ccc");
                return datas;
            }

            @Override
            public int getChosePosition() {
                return 0;
            }
        };
        dialog.show();
    }
}
