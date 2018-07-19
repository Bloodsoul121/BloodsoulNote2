package com.example.cgz.bloodsoulnote2.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.fragment.lifecycle.LifeCycleActivity;
import com.example.cgz.bloodsoulnote2.fragment.viewpager.ViewpagerFragmentActivity;

public class FragmentActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    @Override
    protected void initData() {
        mDatas.add("测试 replace 和 add 时的生命周期调用");
        mDatas.add("测试 viewpager 切换时 fragment 的生命周期调用");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(LifeCycleActivity.class);
                break;
            case 1:
                startActivity(ViewpagerFragmentActivity.class);
                break;
            case 2:
                break;
        }
    }
}
