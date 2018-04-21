package com.example.cgz.bloodsoulnote2.view.system.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class XDemo1Activity extends BaseActivity {

    private List<String> mDatas = new ArrayList<>();

    private Handler mHandler = new Handler();

    private int mRefreshTime = 0;

    private int mLoadMoreTime = 0;

    private XRecyclerView mRecyclerView;

    private XAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xdemo1);

        initView();
    }

    private void initView() {
        mRecyclerView = (XRecyclerView) findViewById(R.id.xre_xrv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // 下拉刷新的样式
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 上滑刷新的样式
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        // 更换刷新图片
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        // 显示时间
        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);

        // 添加头部View, 一定要这样写
        View header = LayoutInflater.from(this).inflate(R.layout.xrecyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
        mRecyclerView.addHeaderView(header);

        // 底部提示语
        mRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
        mRecyclerView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");

        // When the item number of the screen number is list.size-2,we call the onLoadMore
        // 即当底部还剩2条时, 就调用onLoadMore方法, 默认是1
        mRecyclerView.setLimitNumberToCallLoadMore(2);

        mAdapter = new XAdapter();
        mAdapter.setDatas(mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                log("onRefresh");
                mRefreshTime++;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> datas = new ArrayList<>();
                        for (int i = 0; i < 6; i++) {
                            datas.add("mRefreshTime " + mRefreshTime + " - item - " + i);
                        }
                        mDatas.addAll(0, datas);
                        mAdapter.notifyDataSetChanged();

                        if (mRecyclerView != null) {
                            mRecyclerView.refreshComplete();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                log("onLoadMore");
                mLoadMoreTime++;
                if (mLoadMoreTime < 3) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> datas = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                datas.add("mLoadMoreTime " + mLoadMoreTime + " - item - " + i);
                            }
                            mDatas.addAll(datas);
                            mAdapter.notifyDataSetChanged();

                            if (mRecyclerView != null) {
                                mRecyclerView.loadMoreComplete();
                            }
                        }
                    }, 1000);
                } else {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(mRecyclerView != null) {
                                mRecyclerView.setNoMore(true);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }, 1000);
                }
            }
        });

        mAdapter.setClickCallBack(new XAdapter.ItemClickCallBack() {
            @Override
            public void onItemClick(int pos) {
                log(mDatas.size() + " " + pos);
                mDatas.remove(pos);
                mRecyclerView.notifyItemRemoved(mDatas, pos);
            }
        });

        // footview可以替换上滑刷新时底部的加载条
//        TextView tv = new TextView(this);
//        tv.setText("footview");
//        mRecyclerView.setFootView(tv, new CustomFooterViewCallBack() {
//            @Override
//            public void onLoadingMore(View view) {
//
//            }
//
//            @Override
//            public void onLoadMoreComplete(View view) {
//
//            }
//
//            @Override
//            public void onSetNoMore(View view, boolean b) {
//
//            }
//        });

        mRecyclerView.refresh();

    }

}
