package com.example.cgz.bloodsoulnote2.mode.proxy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.mode.proxy.dynamic.ProxyHandler;

public class ProxyActivity extends BaseActivity {

    private static final String TAG = ProxyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
    }

    public void clickBtn1(View view) {
        ProxyHandler proxy = new ProxyHandler();
        IHouse house = (IHouse) proxy.newProxyInstance(new House("妖精的尾巴", 50000));
        house.getHouseInfo();
        Log.i(TAG, "thinking");
        house.signContract();
        house.payFees();
        Log.i(TAG, "so easy");
    }
}
