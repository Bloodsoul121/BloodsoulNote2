package com.example.cgz.bloodsoulnote2.otherframe.arouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.cgz.bloodsoulnote2.R;

@Route(path = "/otherframe/arouter/skip_other_activity")
public class SkipOtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_other);
    }

    public void skip(View view) {
        ARouter.getInstance().build("/otherframe/arouter/arouter_activity").navigation();
    }
}
