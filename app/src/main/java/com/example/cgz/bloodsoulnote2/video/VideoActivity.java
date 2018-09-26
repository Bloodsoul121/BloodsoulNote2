package com.example.cgz.bloodsoulnote2.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.video.vitamio.VitamioActivity;

public class VideoActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
    }

    @Override
    protected void initData() {
        mDatas.add("vitamio");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(VitamioActivity.class);
                break;
        }
    }
}
