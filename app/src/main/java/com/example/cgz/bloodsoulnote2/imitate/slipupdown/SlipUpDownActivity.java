package com.example.cgz.bloodsoulnote2.imitate.slipupdown;

import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.imitate.slipupdown.view.UCIndexView;

public class SlipUpDownActivity extends BaseActivity {

    private UCIndexView mUCIndexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_up_down);
        mUCIndexView = (UCIndexView) findViewById(R.id.ucindexview);
    }

    @Override
    public void onBackPressed() {
        if(mUCIndexView.onBackRestore()) {
            //
        } else {
            super.onBackPressed();
        }
    }

}
