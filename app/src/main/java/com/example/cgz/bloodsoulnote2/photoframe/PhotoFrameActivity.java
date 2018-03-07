package com.example.cgz.bloodsoulnote2.photoframe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.photoframe.uil.UniversalImageLoaderActivity;

public class PhotoFrameActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_frame);
    }

    @Override
    protected void initData() {
        mDatas.add("Universal-Image-Loader");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(UniversalImageLoaderActivity.class);
                break;
        }
    }
}
