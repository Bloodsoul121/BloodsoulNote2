package com.example.cgz.bloodsoulnote2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.ui.colormatrix.ColorMatrixActivity;

public class UIActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
    }

    @Override
    protected void initData() {
        mDatas.add("ColorMatrix 改变图片颜色");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(ColorMatrixActivity.class);
                break;
        }
    }
}
