package com.example.cgz.bloodsoulnote2.view.custom.setting;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cgz.bloodsoulnote2.R;

public class SettingSwitchView extends RelativeLayout implements View.OnClickListener {

    private static final int SWITCH_OFF = 0;
    private static final int SWITCH_ON = 1;

    private ImageView mSwitchBg;
    private ImageView mSwitchBtn;

    private boolean mIsOpen = false;

    private int mSwitchBgWidth;
    private int mSwitchBtnWidth;

    public SettingSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingSwitchView);
        int status = typedArray.getInt(R.styleable.SettingSwitchView_SwitchOnOff, SWITCH_OFF);
        mIsOpen = (status == SWITCH_ON);
        boolean isAutoOnOff = typedArray.getBoolean(R.styleable.SettingSwitchView_AutoOnOff, false);
        setAutoOnOff(isAutoOnOff);
        typedArray.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_setting_switch, this);
        mSwitchBg = (ImageView) findViewById(R.id.switch_bg);
        mSwitchBtn = (ImageView) findViewById(R.id.switch_btn);

        initSwitchOnOff(true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mSwitchBgWidth = mSwitchBg.getMeasuredWidth();
        mSwitchBtnWidth = mSwitchBtn.getMeasuredWidth();
    }

    private void initSwitchOnOff(boolean isOpen) {
        mIsOpen = isOpen;
        if (mIsOpen) {
            mSwitchBg.setImageResource(R.drawable.amigo_switch_track_on);
            mSwitchBtn.setImageResource(R.drawable.amigo_btn_radio_switch_on);
        } else {
            mSwitchBg.setImageResource(R.drawable.amigo_switch_track_off);
            mSwitchBtn.setImageResource(R.drawable.amigo_btn_radio_switch_off);
        }

        RelativeLayout.LayoutParams layoutParams = (LayoutParams) mSwitchBtn.getLayoutParams();
        layoutParams.addRule(mIsOpen ? ALIGN_RIGHT : ALIGN_LEFT, mSwitchBg.getId());
        mSwitchBtn.setLayoutParams(layoutParams);
    }

    public void setSwitchStatus(boolean isOpen) {
        initSwitchOnOff(isOpen);
    }

    public void toggleOff() {
        mSwitchBg.setImageResource(R.drawable.amigo_switch_track_off);
        mSwitchBtn.setImageResource(R.drawable.amigo_btn_radio_switch_off);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mSwitchBtn, "translationX", 0, mSwitchBtnWidth - mSwitchBgWidth);
        animator.setDuration(300);
        animator.start();
    }

    public void toggleOn() {
        mSwitchBg.setImageResource(R.drawable.amigo_switch_track_on);
        mSwitchBtn.setImageResource(R.drawable.amigo_btn_radio_switch_on);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mSwitchBtn, "translationX", mSwitchBtnWidth - mSwitchBgWidth, 0);
        animator.setDuration(300);
        animator.start();
    }

    public void setAutoOnOff(boolean isAuto) {
        if (isAuto) {
            setOnClickListener(this);
        } else {
            setOnClickListener(null);
        }
    }

    @Override
    public void onClick(View v) {
        if (mIsOpen) {
            toggleOff();
        } else {
            toggleOn();
        }
        mIsOpen = !mIsOpen;
    }
}
