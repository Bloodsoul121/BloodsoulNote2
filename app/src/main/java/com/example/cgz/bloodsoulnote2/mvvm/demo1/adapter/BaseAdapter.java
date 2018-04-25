package com.example.cgz.bloodsoulnote2.mvvm.demo1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgz on 18-4-25.
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public Context mContext;
    public List<T> mDatas;
    public LayoutInflater mInflater;

    public BaseAdapter(Context context) {
        this.mContext = context;
        this.mDatas = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVH(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindVH(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 创建 View Holder
     */
    public abstract VH onCreateVH(ViewGroup parent, int viewType);

    /**
     * 绑定 View Holder
     */
    public abstract void onBindVH(VH vh, int position);

    public void refreshData(List<T> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     */
    public void loadMoreData(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

}
