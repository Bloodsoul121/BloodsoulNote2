package com.example.cgz.bloodsoulnote2.net;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.net.httpurlconnection.HttpUrlConnectionActivity;
import com.example.cgz.bloodsoulnote2.net.okhttp.OkhttpActivity;

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
        mDatas.add("OkHttp");
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
                startActivity(OkhttpActivity.class);
                break;
        }
    }
}
