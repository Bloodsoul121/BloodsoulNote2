package com.example.cgz.bloodsoulnote2.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.view.custom.CustomViewActivity;
import com.example.cgz.bloodsoulnote2.view.system.SystemViewActivity;

public class ViewActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }

    @Override
    protected void initData() {
        mDatas.add("System View");
        mDatas.add("Custom View");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(SystemViewActivity.class);
                break;
            case 1:
                startActivity(CustomViewActivity.class);
                break;
        }
    }
}
