package com.example.cgz.bloodsoulnote2.custom.zero;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;

public class CustomScrollHelper {

    public boolean isViewTop(View view) {
        if (view == null) {
            return true;
        }
        if (view instanceof RecyclerView) {
            return isRecyclerViewTop((RecyclerView) view);
        } else if (view instanceof WebView) {
            return isWebViewTop((WebView)view);
        }
        return true;
    }

    private boolean isWebViewTop(WebView webView) {
        if(webView != null) {
            int scrollViewY = webView.getScrollY();
            return scrollViewY <= 0;
        }
        return false;
    }

    private boolean isRecyclerViewTop(RecyclerView recyclerView) {
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

    public boolean isViewBottom(View view) {
        if (view == null) {
            return true;
        }
        if (view instanceof WebView) {
            return isWebViewBottom((WebView) view);
        } else if (view instanceof RecyclerView) {
            return isRecyclerViewBottom((RecyclerView) view);
        }
        return true;
    }

    private boolean isRecyclerViewBottom(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            if(visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1){
                return true;
            }
        }
        return false;
    }

    private boolean isWebViewBottom(WebView webView) {
        float webContentHeight = webView.getContentHeight() * webView.getScale();// webview的高度
        float webNow = webView.getHeight() + webView.getScrollY();// 当前webview的高度
        return webNow/webContentHeight == 1.0f;
    }

    public void smoothScrollToTop(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof WebView) {
            WebView localWebView = (WebView) view;
            localWebView.scrollTo(0, 0);
        } else if (view instanceof RecyclerView) {
            ((RecyclerView)view).smoothScrollToPosition(0);
        }
    }

    public void smoothScrollToPosition(View view, int position) {
        if (view == null) {
            return;
        }
        if (view instanceof RecyclerView) {
            ((RecyclerView)view).smoothScrollToPosition(position);
        } else if (view instanceof ListView) {
            ((ListView)view).smoothScrollToPosition(position);
        }
    }

    public void smoothScrollToBottom(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof WebView) {
            WebView localWebView = (WebView) view;
            int webContentHeight = Math.round(localWebView.getContentHeight() * localWebView.getScale());
            localWebView.scrollTo(0, webContentHeight - localWebView.getHeight());
        } else if (view instanceof RecyclerView) {
            ((RecyclerView)view).smoothScrollToPosition(((RecyclerView)view).getAdapter().getItemCount() - 1);
        }
    }

    public void smoothScrollWithVelocity(View view, int scrollerVelocity) {
        if (view == null) {
            return;
        }
        if (view instanceof WebView) {
            ((WebView) view).flingScroll(0, scrollerVelocity);
        } else if (view instanceof RecyclerView) {
            ((RecyclerView)view).fling(0, scrollerVelocity);
        }
    }

}
