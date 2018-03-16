package com.example.cgz.bloodsoulnote2.imitate;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.imitate.slipupdown.SlipUpDownActivity;

public class ImitateActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitate);
    }

    @Override
    protected void initData() {
        mDatas.add("自定义View\n高仿实现UC浏览器首页上下滑动效果");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(SlipUpDownActivity.class);
                break;
        }
    }
}
