package com.example.cgz.bloodsoulnote2.custom.zero;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomZeroActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.scrollview)
    CustomScrollView mScrollview;

    private Context mContext;
    private List<String> mRecyDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_zero);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mContext = this;
        initWebview();
        initRecyclerView();
    }

    private void initWebview() {
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.setWebChromeClient(new WebChromeClient());
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        mWebview.loadUrl("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9462926354498091897%22%7D&n_type=0&p_from=1");
    }

    private void initRecyclerView() {
        initRecyData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        RecyAdapter recyAdapter = new RecyAdapter();
        mRecyclerview.setAdapter(recyAdapter);
        mRecyclerview.setHasFixedSize(true); // 确保尺寸
    }

    private void initRecyData() {
        mRecyDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mRecyDatas.add("recy - item - " + i);
        }
    }

    public void clickTop(View view) {
        toast("top 拿到啦点击事件");
    }

    private class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.RecyHolder> {

        @Override
        public RecyAdapter.RecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(mContext);
            tv.setPadding(30, 30, 30, 30);
            return new RecyHolder(tv);
        }

        @Override
        public void onBindViewHolder(RecyAdapter.RecyHolder holder, int position) {
            holder.mTextView.setText(mRecyDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mRecyDatas.size();
        }

        class RecyHolder extends RecyclerView.ViewHolder {

            TextView mTextView;

            private RecyHolder(View itemView) {
                super(itemView);
                if (itemView instanceof TextView) {
                    mTextView = (TextView) itemView;
                }
            }
        }

    }


}
