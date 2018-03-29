package com.example.cgz.bloodsoulnote2.xuliehua;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.xuliehua.serializable.SerializableActivity;

public class XuliehuaActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuliehua);
    }

    @Override
    protected void initData() {
        mDatas.add("Serializable");
        mDatas.add("Parcelable");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(SerializableActivity.class);
                break;
            case 1:
                break;
        }
    }
}
