package com.example.cgz.bloodsoulnote2.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.custom.zero.CustomZeroActivity;

public class CustomProjectActivity extends ListViewBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_project);
    }

    @Override
    protected void initData() {
        mDatas.add("自定义View 兼容两个子布局滑动");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(CustomZeroActivity.class);
                break;
        }
    }
}
