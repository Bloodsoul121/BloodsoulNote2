package com.example.cgz.bloodsoulnote2.ui.statusbar;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cgz.bloodsoulnote2.R;

public class StatusbarActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar);

        // true全透明，false半透明
        StatusbarLollipop.translucentStatusBar(this, true);
    }
}
