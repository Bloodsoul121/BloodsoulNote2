package com.example.cgz.bloodsoulnote2.view.system.xrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;

public class XRecyclerViewActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycler_view);
    }

    @Override
    protected void initData() {
        mDatas.add("XRecyclerView的基本使用");
        mDatas.add("XRecyclerView的sticky用法");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(XDemo1Activity.class);
                break;
            case 1:
                startActivity(XDemo2Activity.class);
                break;
        }
    }
}
