package com.example.cgz.bloodsoulnote2.thread;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.thread.handlethread.HandleThreadActivity;
import com.example.cgz.bloodsoulnote2.thread.handlethread.MarketTrendActivity;
import com.example.cgz.bloodsoulnote2.thread.handlethread.MutilDownloadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreadActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listview)
    ListView mListview;

    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        mDatas.add("HandleThread - 使用方法");
        mDatas.add("HandleThread - 模拟大盘走势");
        mDatas.add("HandleThread - 多个下载任务");
    }

    private void initView() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, mDatas);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(HandleThreadActivity.class);
                break;
            case 1:
                startActivity(MarketTrendActivity.class);
                break;
            case 2:
                startActivity(MutilDownloadActivity.class);
                break;
        }
    }
}
