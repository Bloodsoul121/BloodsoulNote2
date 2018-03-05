package com.example.cgz.bloodsoulnote2.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.service.bind.BindService;
import com.example.cgz.bloodsoulnote2.service.bind.MyIntentService;
import com.example.cgz.bloodsoulnote2.service.start.StartService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ServiceActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listview)
    ListView mListview;

    private List<String> mDatas = new ArrayList<>();

    private BindService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {}

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (BindService.MyBinder) service;
            myBinder.startDownload();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initData();
        initView();
    }

    private void initData() {
        mDatas.add("Service - start Service 开启");
        mDatas.add("Service - start Service 销毁");
        mDatas.add("Service - bind Service 开启");
        mDatas.add("Service - bind Service 销毁");
        mDatas.add("Service - IntentService 开启");
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
                startService(new Intent(ServiceActivity.this, StartService.class));
                break;
            case 1:
                stopService(new Intent(ServiceActivity.this, StartService.class));
                break;
            case 2:
                bindService(new Intent(ServiceActivity.this, BindService.class), connection, BIND_AUTO_CREATE);
                break;
            case 3:
                unbindService(connection);
                break;
            case 4:
                startService(new Intent(ServiceActivity.this, MyIntentService.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
