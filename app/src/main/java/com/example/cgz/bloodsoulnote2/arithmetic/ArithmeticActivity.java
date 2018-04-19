package com.example.cgz.bloodsoulnote2.arithmetic;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.ListViewBaseActivity;

public class ArithmeticActivity extends ListViewBaseActivity {

    private int[] arr = new int[]{4,9,7,6,2,8,5,1,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic);
    }

    @Override
    protected void initData() {
        mDatas.add("冒泡排序");
        mDatas.add("选择排序");
        mDatas.add("插入排序");
        mDatas.add("壳排序");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Sort.BubbleSortArray(arr);
                break;
            case 1:
                Sort.SelectSortArray(arr);
                break;
            case 2:
                Sort.InsertSortArray(arr);
                break;
            case 3:
                Sort.ShellSortArray(arr);
                break;
        }
    }
}
