package com.example.cgz.bloodsoulnote2.cache;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;
import com.example.cgz.bloodsoulnote2.cache.disklrucache.DiskLruCacheActivity;
import com.example.cgz.bloodsoulnote2.cache.linked.LinkedHashMapActivity;
import com.example.cgz.bloodsoulnote2.cache.lru.LruCacheActivity;

public class CacheActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
    }

    @Override
    protected void initData() {
        mDatas.add("LruCache");
        mDatas.add("LinkedHashMap");
        mDatas.add("DiskLruCache");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(LruCacheActivity.class);
                break;
            case 1:
                startActivity(LinkedHashMapActivity.class);
                break;
            case 2:
                startActivity(DiskLruCacheActivity.class);
                break;
        }
    }
}
