package com.example.cgz.bloodsoulnote2.net;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.net.httpurlconnection.HttpUrlConnectionActivity;
import com.example.cgz.bloodsoulnote2.net.okhttp.Okhttp2Activity;
import com.example.cgz.bloodsoulnote2.net.okhttp.Okhttp3Activity;
import com.example.cgz.bloodsoulnote2.net.retrofit.Retrofit2Activity;

public class NetActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
    }

    @Override
    protected void initData() {
        mDatas.add("HttpClient");
        mDatas.add("HttpURLConnection");
        mDatas.add("Volley");
        mDatas.add("OkHttp2");
        mDatas.add("OkHttp3");
        mDatas.add("Retrofit2");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
            case 1:
                startActivity(HttpUrlConnectionActivity.class);
                break;
            case 3:
                startActivity(Okhttp2Activity.class);
                break;
            case 4:
                startActivity(Okhttp3Activity.class);
                break;
            case 5:
                startActivity(Retrofit2Activity.class);
                break;
        }
    }
}
