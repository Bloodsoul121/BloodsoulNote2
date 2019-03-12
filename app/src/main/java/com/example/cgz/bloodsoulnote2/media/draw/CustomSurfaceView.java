package com.example.cgz.bloodsoulnote2.media.draw;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mHolder;
    private CustomThread mThread;

    public CustomSurfaceView(Context context) {
        super(context);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mThread = new CustomThread(mHolder);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.mRunning = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.mRunning = false;
        mThread.interrupt();
    }

    private static class CustomThread extends Thread {

        private SurfaceHolder mHolder;
        private boolean mRunning;
        private Canvas mCanvas;
        private Random mRandom;
        private final Paint mPaint;

        private CustomThread(SurfaceHolder holder) {
            this.mHolder = holder;
            mRandom = new Random();

            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(2);
        }

        @Override
        public void run() {
            while (mRunning) {
                draw();
            }
        }

        private void draw() {
            try {

                mCanvas = mHolder.lockCanvas();

                mCanvas.drawColor(Color.YELLOW);

                float r = mRandom.nextFloat();

                mCanvas.drawCircle(mCanvas.getWidth() / 2, mCanvas.getHeight() / 2, mCanvas.getHeight() / 2, mPaint);

                mCanvas.drawCircle(r * mCanvas.getWidth(), r * mCanvas.getHeight(), r * mCanvas.getHeight() / 2, mPaint);

                mHolder.unlockCanvasAndPost(mCanvas);

                sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {

            }
        }
    }

}
