package com.example.cgz.bloodsoulnote2.view.custom.test;

import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class TestCustomViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view);
        log("activity - onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("activity - onResume");
    }
}
