package com.example.cgz.bloodsoulnote2.cache.lru;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class LruCacheActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru_cache);

        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        
    }

    private class MyLruCache extends LruCache{

        /**
         * @param maxSize for caches that do not override {@link #sizeOf}, this is
         *                the maximum number of entries in the cache. For all other caches,
         *                this is the maximum sum of the sizes of the entries in this cache.
         */
        public MyLruCache(int maxSize) {
            super(maxSize);
        }

        @Override
        public void resize(int maxSize) {
            super.resize(maxSize);
        }

        @Override
        protected int sizeOf(Object key, Object value) {
            return super.sizeOf(key, value);
        }

        @Override
        protected Object create(Object key) {
            return super.create(key);
        }

        @Override
        protected void entryRemoved(boolean evicted, Object key, Object oldValue, Object newValue) {
            super.entryRemoved(evicted, key, oldValue, newValue);
        }

        @Override
        public void trimToSize(int maxSize) {
            super.trimToSize(maxSize);
        }

    }

}
