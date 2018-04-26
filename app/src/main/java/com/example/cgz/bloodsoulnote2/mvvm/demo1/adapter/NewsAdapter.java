package com.example.cgz.bloodsoulnote2.mvvm.demo1.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cgz.bloodsoulnote2.BR;
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
        ViewDataBinding binding = viewHolder.getBinding();
        binding.setVariable(BR.simpleNewsBean, mDatas.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }

    public void clickDianZan(SimpleNewsBean simpleNewsBean, int position) {
        if (simpleNewsBean.isGood.get()) {
            simpleNewsBean.isGood.set(false);
            Toast.makeText(mContext, "取消点赞 position=" + position, Toast.LENGTH_SHORT).show();
        } else {
            simpleNewsBean.isGood.set(true);
            Toast.makeText(mContext, "点赞成功 position=" + position, Toast.LENGTH_SHORT).show();
        }
    }
}
