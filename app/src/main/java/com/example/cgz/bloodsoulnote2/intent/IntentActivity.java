package com.example.cgz.bloodsoulnote2.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

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

    public void clickBtn2(View view) {
        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + "4006109993"));
        startActivity(phoneIntent);
    }
}
