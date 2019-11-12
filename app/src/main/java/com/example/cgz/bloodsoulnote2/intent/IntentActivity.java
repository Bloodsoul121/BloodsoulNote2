package com.example.cgz.bloodsoulnote2.intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
    }

    public void clickBtn1(View view) {
//        GoToScoreUtils.goToMarket(this, getPackageName());
        GoToScoreUtils.goToMarket(this, "com.cwj.hsing");
    }
}
