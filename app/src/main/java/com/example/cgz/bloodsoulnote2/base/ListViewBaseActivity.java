package com.example.cgz.bloodsoulnote2.base;

import android.support.annotation.LayoutRes;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cgz.bloodsoulnote2.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class ListViewBaseActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listview)
    ListView mListview;

    protected List<String> mDatas = new ArrayList<>();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initData();
        initView();
    }

    protected abstract void initData();

    private void initView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, mDatas);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(this);
    }

}
