package com.example.cgz.bloodsoulnote2.view.custom.setting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cgz.bloodsoulnote2.R;

import java.util.ArrayList;
import java.util.List;

public class CustomSettingView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "CustomSettingView";

    private static final boolean debugger = true;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_LIST = 1;
    private static final int TYPE_SWITCH = 2;

    private static final int LIST_STYLE_NORMAL = 0;
    private static final int LIST_STYLE_SUBTITLE = 1;
    private static final int LIST_STYLE_RIGHT = 2;

    private int mDefaultTitleColor = Color.parseColor("#000000");
    private int mDefaultSubtitleColor = Color.parseColor("#000000");
    private float mDefaultTitleSize = getResources().getDimension(R.dimen.custom_setting_view_default_title_size);
    private float mDefaultSubtitleSize = getResources().getDimension(R.dimen.custom_setting_view_default_subtitle_size);

    private Context mContext;

    private TextView mTitleView;
    private TextView mSubtitleView;
    private ImageView mSwitchImage;
    private TextView mChoosedTextView;

    private String mAttrTitle;
    private String mAttrSubtitle;
    private int mAttrTitleColor;
    private int mAttrSubtitleColor;
    private float mAttrTitleSize;
    private float mAttrSubtitleSize;
    private int mType;
    private int mListStyle;
    private boolean mIsSwitchOpen;
    private String mDialogTitle;
    private String mDialogExtra;
    private CharSequence[] mDialogKeys;
    private CharSequence[] mDialogValues;
    private List<String> mCastDialogKeys = new ArrayList<>();
    private List<String> mCastDialogValues = new ArrayList<>();
    private String mDialogDefaultKey;
    private CallBack mCallBack;

    private int mOptionsClickPosition = -1;

    public CustomSettingView(Context context) {
        super(context);
    }

    public CustomSettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomSettingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        mContext = context;
        initAttr(context, attrs);
        initView(context);
        initStatus();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSettingView);
        mAttrTitle = typedArray.getString(R.styleable.CustomSettingView_BearTitle);
        mAttrSubtitle = typedArray.getString(R.styleable.CustomSettingView_BearSubtitle);
        mAttrTitleColor = typedArray.getColor(R.styleable.CustomSettingView_BearTitleColor, mDefaultTitleColor);
        mAttrSubtitleColor = typedArray.getColor(R.styleable.CustomSettingView_BearSubtitleColor, mDefaultSubtitleColor);
        mAttrTitleSize = typedArray.getDimension(R.styleable.CustomSettingView_BearTitleSize, mDefaultTitleSize);
        mAttrSubtitleSize = typedArray.getDimension(R.styleable.CustomSettingView_BearSubtitleSize, mDefaultSubtitleSize);
        log("mAttrTitleSize : " + mAttrTitleSize + " , mAttrSubtitleSize : " + mAttrSubtitleSize);
        mType = typedArray.getInteger(R.styleable.CustomSettingView_BearType, TYPE_NORMAL);
        log("type : " + mType);
        mListStyle = typedArray.getInteger(R.styleable.CustomSettingView_BearListStyle, LIST_STYLE_NORMAL);
        log("list_style : " + mListStyle);
        // switch
        mIsSwitchOpen = typedArray.getBoolean(R.styleable.CustomSettingView_BearToggleOpen, false);
        // list
        mDialogTitle = typedArray.getString(R.styleable.CustomSettingView_BearDialogTitle);
        mDialogExtra = typedArray.getString(R.styleable.CustomSettingView_BearDialogExtra);
        mDialogKeys = typedArray.getTextArray(R.styleable.CustomSettingView_BearDialogKey);
        mDialogValues = typedArray.getTextArray(R.styleable.CustomSettingView_BearDialogValue);
        mDialogDefaultKey = typedArray.getString(R.styleable.CustomSettingView_BearDialogDefaultKey);
        typedArray.recycle();
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rootView = layoutInflater.inflate(R.layout.custom_setting_view_base, this);
        RelativeLayout containerView = (RelativeLayout) rootView.findViewById(R.id.setting_root_view);
        mTitleView = (TextView) rootView.findViewById(R.id.title);
        mSubtitleView = (TextView) rootView.findViewById(R.id.subtitle);
        mSwitchImage = (ImageView) rootView.findViewById(R.id.switchimage);
        mChoosedTextView = (TextView) rootView.findViewById(R.id.choosedtext);

        containerView.setOnClickListener(this);
    }

    private void initStatus() {
        handleTitle();
        handleSubtitle();
        handleType();
    }

    private void handleTitle() {
        if (!TextUtils.isEmpty(mAttrTitle)) {
            mTitleView.setText(mAttrTitle);
        }
        mTitleView.setTextColor(mAttrTitleColor);
        mTitleView.setTextSize(mAttrTitleSize);
    }

    private void handleSubtitle() {
        if (!TextUtils.isEmpty(mAttrSubtitle)) {
            mSubtitleView.setText(mAttrSubtitle);
            mSubtitleView.setTextColor(mAttrSubtitleColor);
            mSubtitleView.setTextSize(mAttrSubtitleSize);
            mSubtitleView.setVisibility(VISIBLE);
        } else {
            mSubtitleView.setVisibility(GONE);
        }
    }

    private void handleType() {
        mSwitchImage.setVisibility(GONE);
        switch (mType) {
            case TYPE_NORMAL:
                handleNormal();
                break;
            case TYPE_LIST:
                handleList();
                break;
            case TYPE_SWITCH:
                handleSwitch();
                break;
        }
    }

    private void handleNormal() {

    }

    private void handleList() {
        if (mDialogKeys == null || mDialogValues == null) {
            return;
        }
        if (mDialogKeys.length != mDialogValues.length) {
            throw new IllegalArgumentException("keys must match values !!!");
        }
        for (int i = 0; i < mDialogKeys.length; i++) {
            mCastDialogKeys.add(mDialogKeys[i].toString());
            mCastDialogValues.add(mDialogValues[i].toString());
        }

        handleChoseKeyView();
    }

    private void handleChoseKeyView() {
        switch (mListStyle) {
            case LIST_STYLE_NORMAL:
                break;
            case LIST_STYLE_SUBTITLE:
                mSubtitleView.setVisibility(VISIBLE);
                mSubtitleView.setText(mDialogDefaultKey);
                break;
            case LIST_STYLE_RIGHT:
                mChoosedTextView.setVisibility(VISIBLE);
                mChoosedTextView.setText(mDialogDefaultKey);
                break;
        }
    }

    private void handleSwitch() {
        mSwitchImage.setVisibility(VISIBLE);
        // TODO: 18-3-13

    }

    private void log(String msg) {
        if (debugger) {
            Log.i(TAG, msg);
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        // nothing
    }

    @Override
    public void onClick(View v) {
        log("onclick type --> " + mType);
        switch (mType) {
            case TYPE_NORMAL:
                if (mCallBack != null) {
                    mCallBack.onClickCallBack(getId(), null, null, null, -1);
                }
                break;
            case TYPE_LIST:
                showDialog();
                break;
            case TYPE_SWITCH:
                setSwitchOnOff(!mIsSwitchOpen);
                if (mCallBack != null) {
                    mCallBack.onClickCallBack(getId(), mIsSwitchOpen, null, null, -1);
                }
                break;
        }
    }

    private void showDialog() {
        CustomDialog dialog = new CustomDialog(mContext) {
            @Override
            public String getTitle() {
                return mDialogTitle;
            }

            @Override
            public String getExtra() {
                return mDialogExtra;
            }

            @Override
            public List<String> getItems() {
                return mCastDialogKeys;
            }

            @Override
            public int getChosePosition() {
                if (mOptionsClickPosition == -1) {
                    mOptionsClickPosition = findIndexOfKeys(mDialogDefaultKey);
                }
                return mOptionsClickPosition;
            }
        };
        dialog.setOnOptionsClickListener(new CustomDialog.OnOptionsClickListener() {
            @Override
            public void onOptionsClick(String key, int position) {
                log("key --> " + key + " , position --> " + position);
                mDialogDefaultKey = key;
                mOptionsClickPosition = position;
                handleChoseKeyView();
                if (mCallBack != null) {
                    mCallBack.onClickCallBack(getId(), null, key, mCastDialogValues.get(position), position);
                }
            }
        });
        dialog.show();
    }

    public int findIndexOfKeys(String value) {
        if(value != null && this.mDialogKeys != null) {
            for(int i = this.mDialogKeys.length - 1; i >= 0; --i) {
                if(this.mDialogKeys[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void setSubtitle(String subtitle) {
        mSubtitleView.setVisibility(VISIBLE);
        mSubtitleView.setText(subtitle);
    }

    public void setDialogKeysAndValues(List<String> keys, List<String> values) {
        mCastDialogKeys = keys;
        mCastDialogValues = values;
    }

    public void setDialogDefaultKey(String defaultKey) {
        mDialogDefaultKey = defaultKey;
        mOptionsClickPosition = findIndexOfKeys(mDialogDefaultKey);
    }

    public void setDialogDefaultKeyPosition(int defaultPosition) {
        if (mCastDialogKeys == null) {
            throw new IllegalArgumentException("keys can't be null !!!");
        }
        if (defaultPosition < 0 || defaultPosition >= mCastDialogKeys.size()) {
            throw new IllegalArgumentException("defaultPosition can't below 0 or IndexOutOfBounds !!!");
        }
        mDialogDefaultKey = mCastDialogKeys.get(defaultPosition);
        mOptionsClickPosition = defaultPosition;
    }

    public void setSwitchOnOff(boolean isOpen) {
        if (mType == TYPE_SWITCH) {
            mIsSwitchOpen = isOpen;

        }
    }

    interface CallBack {
        void onClickCallBack(int bearId, Boolean isSwitchOpen, String key, String value, int position);
    }

    public void setOnCallBackListener(CallBack callBack) {
        this.mCallBack = callBack;
    }

}
