package com.example.cgz.bloodsoulnote2.thread.handlethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketTrendActivity extends BaseActivity {

    private static final int UPDATE_INFO = 0x11;

    @BindView(R.id.textview)
    TextView mTextview;

    private Handler mHandler = new Handler();
    private Handler mCheckHandler;
    private HandlerThread mHandlerThread;
    private boolean isUpdateInfo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_trend);
        ButterKnife.bind(this);

        //创建后台线程
        initBackThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckHandler.sendEmptyMessage(UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckHandler.removeMessages(UPDATE_INFO);
    }

    private void initBackThread() {
        mHandlerThread = new HandlerThread("HandlerThread-MarketTrend");
        mHandlerThread.start();

        mCheckHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                log("handleMessage --> msg.what : " + msg.what);
                logCurrentThread();

                checkForUpdate();
                if (isUpdateInfo) {
                    mCheckHandler.sendEmptyMessageDelayed(UPDATE_INFO, 1000);
                }
            }
        };
    }

    /**
     * 模拟网络请求
     */
    private void checkForUpdate() {
        try {
            Thread.sleep(1000);

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    logCurrentThread();

                    String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                    result = String.format(result, (int) (Math.random() * 3000 + 1000));
                    mTextview.setText(Html.fromHtml(result));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源
        mHandlerThread.quit();
    }
}
