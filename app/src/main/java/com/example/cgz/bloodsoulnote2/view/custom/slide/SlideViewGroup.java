package com.example.cgz.bloodsoulnote2.view.custom.slide;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by cgz on 18-4-3.
 */

public class SlideViewGroup extends FrameLayout {

    private static final String TAG = "SlideViewGroup";

    private float mDownX;
    private float mDownY;
    private boolean mIntercept;
    private boolean mIsSolve;
    private float mLastMoveX;
    private float mLastMoveY;

    public SlideViewGroup(Context context) {
        super(context);
    }

    public SlideViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        log("onInterceptTouchEvent --> " + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveX = mDownX = ev.getX();
                mLastMoveY = mDownY = ev.getY();
                mIsSolve = false;
                return false;
            case MotionEvent.ACTION_MOVE:
                log("onInterceptTouchEvent --> mIsSolve " + mIsSolve);
                if (!mIsSolve) {
                    mIsSolve = true;
                    float moveX = ev.getX();
                    float moveY = ev.getY();
                    mIntercept = Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY);
                }
                return mIntercept;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        log("onTouchEvent --> " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                log("onTouchEvent --> " + moveX + ", " + mLastMoveX);
                offsetLeftAndRight((int) (moveX - mLastMoveY));
                break;
        }
        return super.onTouchEvent(event);
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }

}
