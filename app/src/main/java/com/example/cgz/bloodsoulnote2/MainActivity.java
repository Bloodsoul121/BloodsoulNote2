package com.example.cgz.bloodsoulnote2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.activity.ActivityActivity;
import com.example.cgz.bloodsoulnote2.async.AsyncActivity;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.broadcastreceiver.BroadcastReceiverActivity;
import com.example.cgz.bloodsoulnote2.cache.CacheActivity;
import com.example.cgz.bloodsoulnote2.menu.MenuActivity;
import com.example.cgz.bloodsoulnote2.photoframe.PhotoFrameActivity;
import com.example.cgz.bloodsoulnote2.service.ServiceActivity;
import com.example.cgz.bloodsoulnote2.thread.ThreadActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<String> mDatas = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mDatas.add("BloodsoulNote2 - 2018");
        mDatas.add("1 - Activity");
        mDatas.add("2 - BroadcastReceiver");
        mDatas.add("3 - service");
        mDatas.add("4 - provider");
        mDatas.add("5 - Thread");
        mDatas.add("6 - 消息通信");
        mDatas.add("7 - 图片框架");
        mDatas.add("8 - 菜单menu");
        mDatas.add("9 - 缓存Cache");
        mDatas.add("10 - IO流");
        mDatas.add("11 - 网络框架");
    }

    private void clickRecyclerItem(int position) {
        switch (position) {
            case 1:
                startActivity(ActivityActivity.class);
                break;
            case 2:
                startActivity(BroadcastReceiverActivity.class);
                break;
            case 3:
                startActivity(ServiceActivity.class);
                break;
            case 5:
                startActivity(ThreadActivity.class);
            case 6:
                startActivity(AsyncActivity.class);
                break;
            case 7:
                startActivity(PhotoFrameActivity.class);
                break;
            case 8:
                startActivity(MenuActivity.class);
                break;
            case 9:
                startActivity(CacheActivity.class);
                break;
            case 10:
                startActivity(CacheActivity.class);
                break;
            case 11:
                startActivity(CacheActivity.class);
                break;
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true); // 确保尺寸
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_item, null);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            holder.mTextView.setText(mDatas.get(position));
            final int clickPosition = holder.getAdapterPosition();
            if (clickPosition == 0) {
                return;
            }
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerItem(clickPosition);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mDatas == null) {
                return 0;
            }
            return mDatas.size();
        }

        class RecyclerHolder extends RecyclerView.ViewHolder {

            TextView mTextView;

            private RecyclerHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }

}