package com.example.cgz.bloodsoulnote2.mode.proxy;

import android.util.Log;

/**
 * Created by cgz on 18-3-26.
 */

public class ProxyHouse implements IHouse{
    private final String TAG = ProxyHouse.class.getSimpleName();
    private IHouse house;
    public ProxyHouse(IHouse house){
        this.house = house;
    }
    @Override
    public void getHouseInfo() {
        Log.i(TAG, "searching");
        house.getHouseInfo();
        Log.i(TAG, "search finished");
    }

    @Override
    public void signContract() {
        Log.i(TAG, "prepare contract");
        house.signContract();
    }

    @Override
    public void payFees() {
        house.payFees();
    }
}
