package com.example.cgz.bloodsoulnote2.imitate.zhihuad;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;

/**
 * Created by cgz on 18-3-28.
 */

public class AdvertisementView extends View {

    private Context mContext;
    private int mViewWidth;
    private int mViewHeight;
    private int resId = R.drawable.timg;
    private BitmapDrawable mTargetDrawable;
    private int mScaleWidth;
    private int mScaleHeight;
    private float mScale;

    private int[] location = new int[2];
    private int[] recyclerLocation = new int[2];
    private int mMaxDistanceY;
    private float drawableDisY; //图片Y轴的偏移量

    public AdvertisementView(Context context) {
        super(context);
        init(context);
    }

    public AdvertisementView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdvertisementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mViewWidth = measureHandle(getSuggestedMinimumWidth(), widthMeasureSpec);
        mViewHeight = measureHandle(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(mViewWidth, mViewHeight);

        createBitmap();
    }

    private int measureHandle(int defaultSize, int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY || mode == MeasureSpec.AT_MOST) {
            result = size;
        } else {
            result = defaultSize;
        }
        return result;
    }

    private void createBitmap() {
        Resources resources = mContext.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);

        // outWidth是以dp为单位的,需要做一次单位转化
        int resWidthPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, options.outWidth, resources.getDisplayMetrics());
        int resHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, options.outHeight, resources.getDisplayMetrics());

        mScale = 1.0f * mViewWidth / resWidthPx;
        mScaleWidth = (int) (mScale * resWidthPx);
        mScaleHeight = (int) (mScale * resHeightPx);

        options.inSampleSize = calculateInSampleSize(resWidthPx, resHeightPx, mScaleWidth, mScaleHeight);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resId, options);

        Matrix mMatrix = new Matrix();
        mMatrix.postScale(mScale, mScale);
        Bitmap targetBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);

        mTargetDrawable = new BitmapDrawable(resources, targetBitmap);
        bitmap.recycle();

        mMaxDistanceY = -targetBitmap.getHeight() + mViewHeight;

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                getLocationInWindow(location);
                drawableDisY = (recyclerLocation[1] - location[1]) * mScale;
                boundTop();
                invalidate();
            }
        });
    }

    private int calculateInSampleSize(int resWidthPx, int resHeightPx, int scaleWidth, int scaleHeight) {
        int inSampleSize = 1;
        if (resWidthPx > scaleWidth || resHeightPx > scaleHeight) {
            int halfWidth = resWidthPx / 2;
            int halfHeight = resHeightPx / 2;
            while ((halfWidth / inSampleSize > scaleWidth) && (halfHeight / inSampleSize > scaleHeight)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public void setImageResource(@DrawableRes int resId) {
        this.resId = resId;
    }

    private void boundTop() {
        if (drawableDisY > 0) {
            drawableDisY = 0;
        }
        if (drawableDisY < mMaxDistanceY) {
            drawableDisY = mMaxDistanceY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (resId == 0) return;

        canvas.save();
        canvas.translate(0, drawableDisY);
        mTargetDrawable.setBounds(0, 0, mViewWidth, mScaleHeight);
        mTargetDrawable.draw(canvas);
        canvas.restore();
    }

    private RecyclerView recyclerView;
    private RecyclerView.OnScrollListener rvScrollListener;
    private int rvHeight;

    public void bindRecyclerView(RecyclerView recyclerView) {
        if (recyclerView == null || recyclerView.equals(this.recyclerView)) {   //避免重复绑定，造成资源浪费
            return;
        }
        unbindRecyclerView();
        this.recyclerView = recyclerView;
        rvHeight = recyclerView.getLayoutManager().getHeight();
        recyclerView.getLocationInWindow(recyclerLocation);
        recyclerView.addOnScrollListener(rvScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (getTopDistance() > 0 && getTopDistance() + getHeight() < rvHeight) {
                    drawableDisY += dy * mScale;
                    boundTop();
                    invalidate();
                }
            }
        });
    }

    private void unbindRecyclerView() {
        if (rvScrollListener != null) {
            recyclerView.removeOnScrollListener(rvScrollListener);
        }
        recyclerView = null;
    }

    private int getTopDistance() {
        getLocationInWindow(location);
        return location[1] - recyclerLocation[1];
    }
}
