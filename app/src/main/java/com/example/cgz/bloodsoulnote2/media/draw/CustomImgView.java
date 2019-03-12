package com.example.cgz.bloodsoulnote2.media.draw;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;

public class CustomImgView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;

    public CustomImgView(Context context) {
        super(context);
    }

    public CustomImgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d4);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("a", MeasureSpec.getMode(widthMeasureSpec) + " " + MeasureSpec.getMode(heightMeasureSpec));
        int width = resolveSize(mBitmap.getWidth(), widthMeasureSpec);
        int height = resolveSize(mBitmap.getHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect real = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        RectF location = new RectF(0, 0, mWidth, mHeight);
        canvas.drawBitmap(mBitmap, real, location, mPaint);
    }
}
