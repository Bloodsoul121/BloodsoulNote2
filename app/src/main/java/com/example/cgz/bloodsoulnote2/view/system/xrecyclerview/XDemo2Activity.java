package com.example.cgz.bloodsoulnote2.view.system.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.StickyScrollLinearLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class XDemo2Activity extends AppCompatActivity {

    private XRecyclerView mRecyclerView;
    private XAdapter mAdapter;
    private ArrayList<String> listData;
    private int times = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xdemo2);
        initXR();

        final View topView = findViewById(R.id.topView);
        final View tabView = findViewById(R.id.tabView);
        final View content = findViewById(R.id.contentView);
        final StickyScrollLinearLayout s = (StickyScrollLinearLayout) findViewById(R.id.StickyScrollLinearLayout);
        // 这个有点坑哦
        s.setTargetFirstVisiblePosition(0);
        s.addOnLayoutChangeListener(
                new View.OnLayoutChangeListener() {
                    @Override
                    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                        if(s.getContentView() != null)
                            return;
                        // 放在这里是为了等初始化结束后再添加，防止 height 获取 =0
                        // add from here just in cause they height==0
                        s.setInitInterface(
                                new StickyScrollLinearLayout.StickyScrollInitInterface() {
                                    @Override
                                    public View setTopView() {
                                        return topView;
                                    }

                                    @Override
                                    public View setTabView() {
                                        return tabView;
                                    }

                                    @Override
                                    public View setContentView() {
                                        return content;
                                    }
                                }
                        );
                    }
                }
        );
    }

    private void initXR(){
        mRecyclerView = (XRecyclerView) findViewById(R.id.XRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        // i suggest you do not open pull refresh in StickyScroll
        // it maybe case some new problems
        mRecyclerView.setPullRefreshEnabled(false);

        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            for(int i = 0; i < 10 ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < 10 ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times ++;
            }
        });

        listData = new  ArrayList<>();
        for(int i=0;i<20;i++){
            listData.add(" data -- "+i);
        }
        mAdapter = new XAdapter(listData);
        mAdapter.setClickCallBack(
                new XAdapter.ItemClickCallBack() {
                    @Override
                    public void onItemClick(int pos) {
                        // a demo for notifyItemRemoved
                        listData.remove(pos);
                        mRecyclerView.notifyItemRemoved(listData,pos);
                    }
                }
        );
        mRecyclerView.setAdapter(mAdapter);
    }

}
