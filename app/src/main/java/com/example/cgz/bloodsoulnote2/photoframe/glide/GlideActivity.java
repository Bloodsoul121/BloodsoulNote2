package com.example.cgz.bloodsoulnote2.photoframe.glide;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.photoframe.glide.group.GlideImageGroupActivity;

public class GlideActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
    }

    @Override
    protected void initData() {
        mDatas.add("Glide 使用");
        mDatas.add("Glide 模拟QQ讨论组的头像组");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(GlideUseActivity.class);
                break;
            case 1:
                startActivity(GlideImageGroupActivity.class);
                break;
        }
    }
}
