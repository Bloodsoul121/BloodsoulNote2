package com.example.cgz.bloodsoulnote2.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.activity.ActivityActivity;
import com.example.cgz.bloodsoulnote2.arithmetic.ArithmeticActivity;
import com.example.cgz.bloodsoulnote2.async.AsyncActivity;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;
import com.example.cgz.bloodsoulnote2.bluetooth.BlueToothActivity;
import com.example.cgz.bloodsoulnote2.broadcastreceiver.BroadcastReceiverActivity;
import com.example.cgz.bloodsoulnote2.cache.CacheActivity;
import com.example.cgz.bloodsoulnote2.classloader.ClassLoaderActivity;
import com.example.cgz.bloodsoulnote2.custom.CustomProjectActivity;
import com.example.cgz.bloodsoulnote2.fragment.FragmentActivity;
import com.example.cgz.bloodsoulnote2.imitate.ImitateActivity;
import com.example.cgz.bloodsoulnote2.inject.InjectActivity;
import com.example.cgz.bloodsoulnote2.io.IOActivity;
import com.example.cgz.bloodsoulnote2.ipc.IPCActivity;
import com.example.cgz.bloodsoulnote2.jni.JniActivity;
import com.example.cgz.bloodsoulnote2.menu.MenuActivity;
import com.example.cgz.bloodsoulnote2.mode.ModeActivity;
import com.example.cgz.bloodsoulnote2.mvvm.MvvmActivity;
import com.example.cgz.bloodsoulnote2.net.NetActivity;
import com.example.cgz.bloodsoulnote2.otherframe.OtherFrameActivity;
import com.example.cgz.bloodsoulnote2.photoframe.PhotoFrameActivity;
import com.example.cgz.bloodsoulnote2.service.ServiceActivity;
import com.example.cgz.bloodsoulnote2.thread.ThreadActivity;
import com.example.cgz.bloodsoulnote2.ui.UIActivity;
import com.example.cgz.bloodsoulnote2.view.ViewActivity;
import com.example.cgz.bloodsoulnote2.window.WindowActivity;
import com.example.cgz.bloodsoulnote2.xuliehua.XuliehuaActivity;

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
        mDatas.add("12 - Android View");
        mDatas.add("13 - 颜色 布局 矩阵");
        mDatas.add("14 - 进程通信 IPC");
        mDatas.add("15 - 高仿项目");
        mDatas.add("16 - 自定义项目");
        mDatas.add("17 - 设计模式");
        mDatas.add("18 - JNI");
        mDatas.add("19 - 序列化");
        mDatas.add("20 - 依赖注入");
        mDatas.add("21 - Window");
        mDatas.add("22 - 类加载器");
        mDatas.add("23 - 数据结构与算法");
        mDatas.add("24 - Fragment");
        mDatas.add("25 - ");
        mDatas.add("26 - MVVM模式");
        mDatas.add("27 - 蓝牙");
        mDatas.add("28 - 三方框架");
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
                startActivity(IOActivity.class);
                break;
            case 11:
                startActivity(NetActivity.class);
                break;
            case 12:
                startActivity(ViewActivity.class);
                break;
            case 13:
                startActivity(UIActivity.class);
                break;
            case 14:
                startActivity(IPCActivity.class);
                break;
            case 15:
                startActivity(ImitateActivity.class);
                break;
            case 16:
                startActivity(CustomProjectActivity.class);
                break;
            case 17:
                startActivity(ModeActivity.class);
                break;
            case 18:
                startActivity(JniActivity.class);
                break;
            case 19:
                startActivity(XuliehuaActivity.class);
                break;
            case 20:
                startActivity(InjectActivity.class);
                break;
            case 21:
                startActivity(WindowActivity.class);
                break;
            case 22:
                startActivity(ClassLoaderActivity.class);
                break;
            case 23:
                startActivity(ArithmeticActivity.class);
                break;
            case 24:
                startActivity(FragmentActivity.class);
                break;
            case 25:

                break;
            case 26:
                startActivity(MvvmActivity.class);
                break;
            case 27:
                startActivity(BlueToothActivity.class);
                break;
            case 28:
                startActivity(OtherFrameActivity.class);
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
