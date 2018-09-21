package com.example.cgz.bloodsoulnote2.otherframe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.otherframe.databinding.DataBindingActivity;
import com.example.cgz.bloodsoulnote2.otherframe.dbflow.DbflowActivity;
import com.example.cgz.bloodsoulnote2.otherframe.dragger2.Dragger2Activity;
import com.example.cgz.bloodsoulnote2.otherframe.greendao.GreendaoActivity;

public class OtherFrameActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_frame);
    }

    @Override
    protected void initData() {
        mDatas.add("greendao");
        mDatas.add("dbflow");
        mDatas.add("databinding");
        mDatas.add("dragger2");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(GreendaoActivity.class);
                break;
            case 1:
                startActivity(DbflowActivity.class);
                break;
            case 2:
                startActivity(DataBindingActivity.class);
                break;
            case 3:
                startActivity(Dragger2Activity.class);
                break;
        }
    }
}
