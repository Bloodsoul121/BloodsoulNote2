package com.example.cgz.bloodsoulnote2.view.system;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.view.system.dialog.DialogActivity;

public class SystemViewActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_view);
    }

    @Override
    protected void initData() {
        mDatas.add("Dialog");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(DialogActivity.class);
                break;
        }
    }
}
