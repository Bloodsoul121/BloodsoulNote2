package com.example.cgz.bloodsoulnote2.view.custom.test;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by cgz on 18-4-2.
 */

public class TestCustomView extends View{

    private static final String TAG = "TestCustomView";

    public TestCustomView(Context context) {
        super(context);
    }

    public TestCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        log("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        log("onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        log("onDraw");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        log("onFinishInflate");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        log("onSizeChanged");
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 04-02 10:14:45.340 2083-2083/com.example.cgz.bloodsoulnote2 I/TestCustomView: onFinishInflate
     * 04-02 10:14:45.352 2083-2083/com.example.cgz.bloodsoulnote2 I/TestCustomView: onMeasure
     * 04-02 10:14:45.387 2083-2083/com.example.cgz.bloodsoulnote2 I/TestCustomView: onSizeChanged
     * 04-02 10:14:45.387 2083-2083/com.example.cgz.bloodsoulnote2 I/TestCustomView: onLayout
     * 04-02 10:14:45.397 2083-2083/com.example.cgz.bloodsoulnote2 I/TestCustomView: onMeasure
     * 04-02 10:14:45.397 2083-2083/com.example.cgz.bloodsoulnote2 I/TestCustomView: onLayout
     * 04-02 10:14:45.398 2083-2083/com.example.cgz.bloodsoulnote2 I/TestCustomView: onDraw
     */

}
