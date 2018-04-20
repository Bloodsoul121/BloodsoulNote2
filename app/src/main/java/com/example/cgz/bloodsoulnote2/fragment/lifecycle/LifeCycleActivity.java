package com.example.cgz.bloodsoulnote2.fragment.lifecycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class LifeCycleActivity extends BaseActivity {

    private FragmentTransaction mTransaction;
    private FragmentManager mFragmentManager;
    private LifeCycleFragment mFragment3;
    private LifeCycleFragment mFragment4;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle2);

        mFragmentManager = getSupportFragmentManager();

    }

    public void clickBtn1(View view) {
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.container, LifeCycleFragment.newInstance("fragment - 1"));
        mTransaction.commit();
    }

    public void clickBtn2(View view) {
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.container, LifeCycleFragment.newInstance("fragment - 2"));
        mTransaction.commit();
    }

    public void clickBtn3(View view) {
        mTransaction = mFragmentManager.beginTransaction();
        mFragment3 = (LifeCycleFragment) mFragmentManager.findFragmentByTag("fragment3");
        if (mFragment3 == null) {
            mFragment3 = LifeCycleFragment.newInstance("fragment - 3");
            mTransaction.add(R.id.container, mFragment3, "fragment3");
        }
        mTransaction.show(mFragment3);
        mFragment4 = (LifeCycleFragment) mFragmentManager.findFragmentByTag("fragment4");
        if (mFragment4 != null) {
            mTransaction.hide(mFragment4);
        }
        mTransaction.commit();
    }

    public void clickBtn4(View view) {
        mTransaction = mFragmentManager.beginTransaction();
        mFragment4 = (LifeCycleFragment) mFragmentManager.findFragmentByTag("fragment4");
        if (mFragment4 == null) {
            mFragment4 = LifeCycleFragment.newInstance("fragment - 4");
            mTransaction.add(R.id.container, mFragment4, "fragment4");
        }
        mTransaction.show(mFragment4);
        mFragment3 = (LifeCycleFragment) mFragmentManager.findFragmentByTag("fragment3");
        if (mFragment3 != null) {
            mTransaction.hide(mFragment3);
        }
        mTransaction.commit();
    }
}
