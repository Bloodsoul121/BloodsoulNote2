package com.example.cgz.bloodsoulnote2.mvvm.demo1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.databinding.ActivityMvvmBinding;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.adapter.NewsAdapter;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.i.INewsView;
import com.example.cgz.bloodsoulnote2.mvvm.demo1.viewmodel.NewsVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MvvmDemo1Activity extends BaseActivity implements INewsView{

    private ActivityMvvmBinding mBinding;
    private NewsVM mNewsVM;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mvvm);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        initView();
        mNewsVM = new NewsVM(this, mAdapter);
    }

    private void initView() {
        mBinding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mBinding.newsRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                mNewsVM.loadRefreshData();
            }

            @Override
            public void onLoadMore() {
                //上拉加载更多
                mNewsVM.loadMoreData();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.newsRv.setLayoutManager(linearLayoutManager);

        mAdapter = new NewsAdapter(this);
        mBinding.newsRv.setAdapter(mAdapter);
    }

    @Override
    public void loadStart(int loadType) {

    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void loadFailure(String message) {

    }
}
