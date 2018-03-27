package com.example.cgz.bloodsoulnote2.jni;

import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.jnilib.JniLib;

public class JniActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
    }

    public void clickBtn1(View view) {
        JniLib.getStringFromC();
    }

}
