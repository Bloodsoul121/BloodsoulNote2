package com.example.cgz.bloodsoulnote2.custom.zero;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Px;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class CustomScrollView extends FrameLayout {

    private static final String TAG = "CustomScrollView";

    private View mHeadView;
    private RecyclerView mBottomView;
    private int mMinY = 0;
    private int mMaxY = 0;
    private float mLastY;
    private int mMaximumVelocity;
    private Scroller mScroller;
    private DIRECTION mDirection;
    private VelocityTracker mVelocityTracker;
    private int mLastScrollerY;
    private boolean mIsWebViewCanMove = true;//手动操作滑动时，禁止WebView的滚动

    public CustomScrollView(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    private enum DIRECTION {
        UP,// 向上划
        DOWN// 向下划
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        initChildrenView(childCount);
        updateCanScrollMaxLength(childCount);
    }

    private void initChildrenView(int childCount) {
        if (childCount > 0 && mHeadView == null) {
            mHeadView = getChildAt(0);
            mHeadView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return !mIsWebViewCanMove;
                }
            });
        }
        if (childCount > 1 && mBottomView == null) {
            mBottomView = (RecyclerView) getChildAt(1);
        }
    }

    private void updateCanScrollMaxLength(int childCount) {
        if (childCount > 1 && mBottomView != null) {
            mMaxY = mBottomView.getMeasuredHeight();
        } else {
            mMaxY = 0;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (mHeadView != null) {
            mHeadView.layout(left, top, right, bottom);
            if (mBottomView != null) {
                int delta = mBottomView.getBottom() - (bottom + mBottomView.getMeasuredHeight());
                if (delta > 0) {
                    scrollTo(0, mMaxY);
                }
                mBottomView.layout(left, bottom, right, bottom + mBottomView.getMeasuredHeight());
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        float currentY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = currentY;
                initOrResetVelocityTracker(event);
                mScroller.forceFinished(true);
                break;
            case MotionEvent.ACTION_MOVE:
                initVelocityTrackerIfNotExists(event);
                smoothByHand(getViewStatus(), mLastY - currentY);
                mLastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                autoFling();
                mIsWebViewCanMove = true;
                break;
            case MotionEvent.ACTION_CANCEL:
                stopVelocityTracker();
                break;
        }
        return result;
    }

    private void autoFling() {
        int yVelocity = getScrollVelocityY();
        mDirection = yVelocity > 0 ? DIRECTION.UP : DIRECTION.DOWN;
        mScroller.fling(0, getScrollY(), 0, yVelocity, 0, 0, -Integer.MAX_VALUE, Integer.MAX_VALUE);
        mScroller.computeScrollOffset();
        mLastScrollerY = getScrollY();
        invalidate();
    }

    private void smoothByHand(int status, float deltaY) {
        int yVelocity = getScrollVelocityY();
        mDirection = yVelocity > 0 ? DIRECTION.UP : DIRECTION.DOWN;
        if (status == 0) {
            mIsWebViewCanMove = false;
            smoothBottomToTop();
            scrollBy(0, Math.round(deltaY));
        } else if (isRecyclerViewTop() && mDirection == DIRECTION.DOWN && status == 2) {
            mIsWebViewCanMove = false;
            scrollBy(0, Math.round(deltaY));
        } else if (isWebViewBottom() && mDirection == DIRECTION.UP && status == 1){
            mIsWebViewCanMove = false;
            smoothBottomToTop();
            scrollBy(0, Math.round(deltaY));
        } else {
            mIsWebViewCanMove = true;
        }
    }

    private int getViewStatus() {
        if (mBottomView == null || mHeadView == null) {
            return -1;
        }
        boolean isBottomVisible = mBottomView.getGlobalVisibleRect(new Rect());
        boolean isHeadVisible = mHeadView.getGlobalVisibleRect(new Rect());
        if (isBottomVisible && isHeadVisible) {
            return 0;
        } else if (isHeadVisible) {
            return 1;
        } else if (isBottomVisible) {
            return 2;
        }
        return -1;
    }

    @Override
    public void scrollBy(int x, int y) {
        int scrollY = getScrollY();
        int toY = scrollY + y;
        if (toY >= mMaxY) {
            toY = mMaxY;
        } else if (toY <= mMinY) {
            toY = mMinY;
        }
        y = toY - scrollY;
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        if (y >= mMaxY) {
            y = mMaxY;
        } else if (y <= mMinY) {
            y = mMinY;
        }
        super.scrollTo(x, y);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int currY = mScroller.getCurrY();
            int status = getViewStatus();

            if (autoScroll(currY, status)) {
                return;
            }

            childrenAutoScroll(currY, status);

            mLastScrollerY = currY;
            invalidate();
        }
    }

    public void childrenAutoScroll(int currY, int status) {
        int distance = mScroller.getFinalY() - currY;
        int duration = mScroller.getDuration() - mScroller.timePassed();
        int velocity = getScrollerVelocity(distance, duration);
        if (isRecyclerViewTop() && mDirection == DIRECTION.UP && status == 2) {// 手势向上划
            smoothScrollByBottomView(velocity);
            mScroller.forceFinished(true);
            Log.i(TAG, "computeScroll up");
        } else if (isWebViewBottom() && mDirection == DIRECTION.DOWN && status == 1){// 手势向下划
            smoothScrollByHeadView(-velocity);
            mScroller.forceFinished(true);
            Log.i(TAG, "computeScroll down");
        }
    }

    public boolean autoScroll(int currY, int status) {
        if (status == 0
                || (isRecyclerViewTop() && mDirection == DIRECTION.DOWN && status == 2)
                || (isWebViewBottom() && mDirection == DIRECTION.UP && status == 1)) {
            autoScrollBy(0, Math.round((currY - mLastScrollerY)));
            invalidate();
            mLastScrollerY = currY;
            return true;
        }
        return false;
    }

    private void autoScrollBy(int v, int deltaY) {
        scrollBy(0, deltaY);
        smoothBottomToTop();
        smoothScrollWebViewToBottom();
    }

    private int getScrollerVelocity(int distance, int duration) {
        if (mScroller == null) {
            return 0;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return (int) mScroller.getCurrVelocity();
        } else {
            return distance / duration;
        }
    }

    private boolean isWebViewBottom(){
        float approximation = 1.0f;
        float webContentHeight;
        float webNow;
        if (mHeadView instanceof WebView) {
            WebView localWebView = (WebView) mHeadView;
            webContentHeight = localWebView.getContentHeight() * localWebView.getScale();// webview的高度
            webNow = localWebView.getHeight() + localWebView.getScrollY();// 当前webview的高度
            return webNow/webContentHeight == approximation;
        }
        return false;
    }

    private boolean isRecyclerViewTop() {
        if (mBottomView == null) {
            return true;
        }
        RecyclerView recyclerView = mBottomView;
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                View childAt = recyclerView.getChildAt(0);
                if (childAt == null || (firstVisibleItemPosition == 0 &&
                        layoutManager.getDecoratedTop(childAt) == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void initOrResetVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
        mVelocityTracker.addMovement(event);
    }

    private void initVelocityTrackerIfNotExists(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void stopVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private int getScrollVelocityY() {
        if (mVelocityTracker != null) {
            // 设置单位, 1000表示1s内移动的像素值
            mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
            return -(int) mVelocityTracker.getYVelocity();
        } else {
            return 0;
        }
    }

    private void smoothScrollByHeadView(int scrollerVelocity) {
        if (mHeadView instanceof WebView) {
            ((WebView) mHeadView).flingScroll(0, scrollerVelocity);
        }
    }

    private void smoothScrollByBottomView(int scrollerVelocity) {
        if (mBottomView != null) {
            mBottomView.fling(0, scrollerVelocity);
        }
    }

    public void smoothScrollToTop() {
        smoothBottomToTop();
        smoothScrollWebViewToTop();
        scrollTo(0, 0);
    }

    private void smoothBottomToTop() {
        if (mBottomView != null) {
            mBottomView.scrollToPosition(0);
        }
    }

    public void smoothScrollToMostHot() {
        smoothScrollWebViewToBottom();
        if (mBottomView != null) {
            mBottomView.smoothScrollToPosition(0);
        }
        scrollTo(0, mMaxY);
    }

    public void smoothScrollToMostNew(int position) {
        smoothScrollWebViewToBottom();
        if (mBottomView != null) {
            mBottomView.smoothScrollToPosition(position);
        }
        scrollTo(0, mMaxY);
    }

    private void smoothScrollWebViewToTop() {
        if (mHeadView instanceof WebView) {
            WebView localWebView = (WebView) mHeadView;
            localWebView.scrollTo(0, 0);
        }
    }

    private void smoothScrollWebViewToBottom() {
        int webContentHeight;
        if (mHeadView instanceof WebView) {
            WebView localWebView = (WebView) mHeadView;
            webContentHeight = Math.round(localWebView.getContentHeight() * localWebView.getScale());
            localWebView.scrollTo(0, webContentHeight - localWebView.getHeight());
        }
    }

}
