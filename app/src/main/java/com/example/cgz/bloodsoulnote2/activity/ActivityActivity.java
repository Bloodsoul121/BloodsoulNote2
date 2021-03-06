package com.example.cgz.bloodsoulnote2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.activity.lifecycle.LifeCycleActivity;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivityActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listview)
    ListView mListview;

    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        initData();
        initView();
    }

    private void initData() {
        mDatas.add("Activity - 生命周期");
    }

    private void initView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, mDatas);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(LifeCycleActivity.class);
                break;
        }
    }
}
