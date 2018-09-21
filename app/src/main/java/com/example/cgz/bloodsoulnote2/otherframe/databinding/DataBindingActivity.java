package com.example.cgz.bloodsoulnote2.otherframe.databinding;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.otherframe.databinding.demo1.Demo1Activity;
import com.example.cgz.bloodsoulnote2.otherframe.databinding.demo2.Demo2Activity;
import com.example.cgz.bloodsoulnote2.otherframe.databinding.demo3.Demo3Activity;

public class DataBindingActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);
    }

    @Override
    protected void initData() {
        mDatas.add("demo1");
        mDatas.add("demo2");
        mDatas.add("demo3");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(Demo1Activity.class);
                break;
            case 1:
                startActivity(Demo2Activity.class);
                break;
            case 2:
                startActivity(Demo3Activity.class);
                break;
        }
    }
}
