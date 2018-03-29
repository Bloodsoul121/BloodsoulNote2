package com.example.cgz.bloodsoulnote2.ipc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.ipc.aidl.AidlActivity;
import com.example.cgz.bloodsoulnote2.ipc.messenger.MessengerActivity;
import com.example.cgz.bloodsoulnote2.ipc.socket.SocketActivity;

public class IPCActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
    }

    @Override
    protected void initData() {
        mDatas.add("Socket 通信");
        mDatas.add("Binder");
        mDatas.add("AIDL");
        mDatas.add("Messenger - 对AIDL的封装");
        mDatas.add("ContentProvider - 底层Binder");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(SocketActivity.class);
                break;
            case 2:
                startActivity(AidlActivity.class);
                break;
            case 3:
                startActivity(MessengerActivity.class);
                break;
        }
    }
}
