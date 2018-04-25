package com.example.cgz.bloodsoulnote2.mvvm.demo1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.databinding.ActivityMvvmBinding;
import com.example.cgz.bloodsoulnote2.view.system.xrecyclerview.XAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MvvmActivity extends BaseActivity {

    private ActivityMvvmBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mvvm);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        initView();
    }

    private void initView() {
        mBinding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mBinding.newsRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.newsRv.setLayoutManager(linearLayoutManager);

        XAdapter adapter = new XAdapter();
        mBinding.newsRv.setAdapter(adapter);
    }
}
