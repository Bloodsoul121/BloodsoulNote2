package com.example.cgz.bloodsoulnote2.view.custom.slide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cgz on 18-4-3.
 */

public class SlideView extends View {

    private static final String TAG = "SlideView";

    private float mLastMoveX;
    private float mLastMoveY;

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        log("dispatchTouchEvent --> " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        log("onTouchEvent --> " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveX = event.getX();
                mLastMoveY = event.getY();
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                offsetTopAndBottom((int) (moveY - mLastMoveY));
        }
        return true;
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}
