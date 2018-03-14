package com.example.cgz.bloodsoulnote2.view.custom.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;

import java.util.List;

public abstract class CustomDialog extends Dialog implements AdapterView.OnItemClickListener, View.OnClickListener {

    private Context mContext;
    private DialogListViewAdapter mAdapter;
    private List<String> mKeys;
    private OnOptionsClickListener mOnOptionsClickListener;

    public CustomDialog(@NonNull Context context) {
        super(context, R.style.custom_dialog_style);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置dialog的显示位置
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        // 加载自己的布局
        setContentView(R.layout.custom_dialog);

        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth();
        getWindow().setAttributes(lp);

        setCanceledOnTouchOutside(true);

        initView();
    }

    private void initView() {
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView extraView = (TextView) findViewById(R.id.extra);
        TextView cancelView = (TextView) findViewById(R.id.cancel);
        ListView listView = (ListView) findViewById(R.id.listview);

        String title = getTitle();
        if (TextUtils.isEmpty(title)) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(title);
        }

        String extra = getExtra();
        if (TextUtils.isEmpty(extra)) {
            extraView.setVisibility(View.GONE);
        } else {
            extraView.setVisibility(View.VISIBLE);
            extraView.setText(extra);
        }

        mAdapter = new DialogListViewAdapter(mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        mKeys = getItems();
        mAdapter.setDatas(mKeys, getChosePosition());

        cancelView.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.refresh(position);
        dismiss();
        if (mOnOptionsClickListener != null) {
            String key = mKeys.get(position);
            mOnOptionsClickListener.onOptionsClick(key, position);
        }
    }

    public abstract String getTitle();
    public abstract String getExtra();
    public abstract List<String> getItems();
    public abstract int getChosePosition();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
                break;
        }
    }

    interface OnOptionsClickListener {
        void onOptionsClick(String key, int position);
    }

    public void setOnOptionsClickListener(OnOptionsClickListener onOptionsClickListener) {
        this.mOnOptionsClickListener = onOptionsClickListener;
    }

}
