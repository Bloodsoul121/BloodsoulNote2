package com.example.cgz.bloodsoulnote2.imitate.zhihuad;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

public class ZhihuAdActivity extends BaseActivity {

    private static final int LIST_TYPE_AD = 0x11;
    private static final int LIST_TYPE_NORMAL = LIST_TYPE_AD + 1;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_ad);

        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        @Override
        public int getItemViewType(int position) {
            if (position == 10) {
                return LIST_TYPE_AD;
            }
            return LIST_TYPE_NORMAL;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (viewType == LIST_TYPE_AD) {
                view = View.inflate(ZhihuAdActivity.this, R.layout.item_zhihu_ad, null);
            } else {
                view = View.inflate(ZhihuAdActivity.this, R.layout.item_zhihu_normal, null);
                RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
            }
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            if (position == 10) {
                holder.windowImageView.bindRecyclerView(mRecyclerView);
                holder.windowImageView.setImageResource(R.drawable.timg);
            } else {
                holder.itemView.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            }
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class MyHolder extends RecyclerView.ViewHolder {

            private AdvertisementImageView2 windowImageView;

            private MyHolder(View itemView) {
                super(itemView);
                windowImageView = (AdvertisementImageView2) itemView.findViewById(R.id.wiv);
            }
        }
    }
}
