package com.example.cgz.bloodsoulnote2.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.MvvmDemo1Activity;

public class MvvmActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm2);
    }

    @Override
    protected void initData() {
        mDatas.add("mvvm demo1");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(MvvmDemo1Activity.class);
                break;
        }
    }
}
