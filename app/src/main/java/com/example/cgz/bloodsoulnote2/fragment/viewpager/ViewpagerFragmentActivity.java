package com.example.cgz.bloodsoulnote2.fragment.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerFragmentActivity extends BaseActivity {

    private List<Fragment> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment);

        initData();
        init();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(TestFragment.newInstance("fragment-1"));
        mDatas.add(TestFragment.newInstance("fragment-2"));
        mDatas.add(TestFragment.newInstance("fragment-3"));
        mDatas.add(TestFragment.newInstance("fragment-4"));
    }

    private void init() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (mDatas == null) {
                return null;
            }
            return mDatas.get(position);
        }

        @Override
        public int getCount() {
            if (mDatas == null) {
                return 0;
            }
            return mDatas.size();
        }
    }
}
