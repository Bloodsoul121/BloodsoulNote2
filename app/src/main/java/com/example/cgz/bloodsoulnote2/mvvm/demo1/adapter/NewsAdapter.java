package com.example.cgz.bloodsoulnote2.mvvm.demo1.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.bean.SimpleNewsBean;

/**
 * Created by cgz on 18-4-23.
 */

public class NewsAdapter extends BaseAdapter<SimpleNewsBean, ViewHolder> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, R.layout.item_news, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindVH(ViewHolder viewHolder, int position) {

    }
}
