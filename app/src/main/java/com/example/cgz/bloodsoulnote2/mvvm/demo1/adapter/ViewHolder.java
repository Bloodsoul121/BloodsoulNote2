package com.example.cgz.bloodsoulnote2.mvvm.demo1.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by cgz on 18-4-25.
 */

public class ViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private B mBinding;

    public ViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public B getBinding() {
        return mBinding;
    }

}
