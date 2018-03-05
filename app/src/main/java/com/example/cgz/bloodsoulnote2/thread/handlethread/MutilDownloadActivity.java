package com.example.cgz.bloodsoulnote2.thread.handlethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MutilDownloadActivity extends BaseActivity implements Handler.Callback {

    @BindView(R.id.tv_start_msg)
    TextView mTvStartMsg;
    @BindView(R.id.tv_finish_msg)
    TextView mTvFinishMsg;
    @BindView(R.id.btn_start_download)
    Button mBtnStartDownload;

    private Handler mUIHandler;
    private DownloadThread mDownloadThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil_download);

        init();
    }

    private void init() {
        mUIHandler = new Handler(this);
        mDownloadThread = new DownloadThread("download-thread");
        mDownloadThread.setUIHandle(mUIHandler);
        mDownloadThread.setDownloadUrls("http://pan.baidu.com/s/1qYc3EDQ",
                "http://bbs.005.tv/thread-589833-1-1.html", "http://list.youku.com/show/id_zc51e1d547a5b11e2a19e.html?");
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case DownloadThread.TYPE_START:
                mTvStartMsg.setText(mTvStartMsg.getText().toString() + "\n " + msg.obj);
                break;
            case DownloadThread.TYPE_FINISHED:
                mTvFinishMsg.setText(mTvFinishMsg.getText().toString() + "\n " + msg.obj);
                break;
        }
        return true;
    }

    @OnClick(R.id.btn_start_download)
    public void startDownload() {
        mDownloadThread.start();
        mBtnStartDownload.setText("正在下载");
        mBtnStartDownload.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadThread.quitSafely();
    }
}
