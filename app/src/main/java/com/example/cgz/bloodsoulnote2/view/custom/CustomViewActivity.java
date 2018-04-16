package com.example.cgz.bloodsoulnote2.view.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.view.custom.setting.SettingActivity;
import com.example.cgz.bloodsoulnote2.view.custom.slide.SlideConflictActivity;
import com.example.cgz.bloodsoulnote2.view.custom.test.TestCustomViewActivity;

public class CustomViewActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    @Override
    protected void initData() {
        mDatas.add("设置界面");
        mDatas.add("测试自定义view的方法调用周期");
        mDatas.add("分析源码, 处理滑动冲突事件");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(SettingActivity.class);
                break;
            case 1:
                startActivity(TestCustomViewActivity.class);
                break;
            case 2:
                startActivity(SlideConflictActivity.class);
                break;
        }
    }
}
