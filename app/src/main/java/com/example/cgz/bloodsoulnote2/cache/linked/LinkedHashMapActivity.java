package com.example.cgz.bloodsoulnote2.cache.linked;

import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapActivity extends BaseActivity {

    private static final String TAG = "LinkedHashMapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_hash_map);
    }

    public void orderVisit(View view) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0, 0.75f, true);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.get(1);
        map.get(3);

        for (Map.Entry entry : map.entrySet()) {
            Log(TAG, "key --> " + entry.getKey() + ", value --> " + entry.getValue());
        }

        /*
        最近访问的, 最后输出

        03-07 15:06:17.286 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 0, value --> 0
        03-07 15:06:17.286 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 2, value --> 2
        03-07 15:06:17.286 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 4, value --> 4
        03-07 15:06:17.286 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 5, value --> 5
        03-07 15:06:17.286 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 1, value --> 1
        03-07 15:06:17.286 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 3, value --> 3
         */
    }

    public void orderInsert(View view) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0, 0.75f, false);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.get(1);
        map.get(3);

        for (Map.Entry entry : map.entrySet()) {
            Log(TAG, "key --> " + entry.getKey() + ", value --> " + entry.getValue());
        }

        /*
        先插入的, 先输出

        03-07 15:07:39.960 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 0, value --> 0
        03-07 15:07:39.960 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 1, value --> 1
        03-07 15:07:39.960 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 2, value --> 2
        03-07 15:07:39.961 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 3, value --> 3
        03-07 15:07:39.961 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 4, value --> 4
        03-07 15:07:39.961 26760-26760/com.example.cgz.bloodsoulnote2 I/LinkedHashMapActivity: key --> 5, value --> 5
         */
    }
}
