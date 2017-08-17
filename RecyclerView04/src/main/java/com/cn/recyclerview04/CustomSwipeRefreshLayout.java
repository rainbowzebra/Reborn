package com.cn.recyclerview04;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    //是否可上拉加载更多,默认为false
    protected boolean enableLoadMoreData =false;
    //是否可下拉刷新，默认为true
    private boolean enablePullToRefresh = true;
    //是否在加载数据，默认为false
    private boolean isLoading = false;
    private Handler handler;
    private OnSwipeRefreshLayoutListener refreshLayoutListener;

    public CustomSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeColors(Color.parseColor("#62A8DB"), Color.RED);
        handler = new Handler();
        setOnRefreshListener(this);
    }


    @Override
    public boolean isEnabled() {
        if (!enablePullToRefresh) {
            return false;
        }

        if (isLoading) {
            return false;
        }

        return super.isEnabled();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (isLoading) {
            return;
        }

        if (refreshing && isRefreshing() != refreshing) {
            onRefresh();
        }
        super.setRefreshing(refreshing);
    }

    @Override
    public void onRefresh() {
        isLoading = true;
        if (refreshLayoutListener != null) {
            refreshLayoutListener.onHeaderRefreshing();
        }
    }

    //是否可上拉加载更多数据，true为可
    public void setEnableLoadMoreData(boolean enable) {
        if (!enablePullToRefresh){
            return;
        }
        enableLoadMoreData = enable;
    }


    //设置下拉刷新是否可用，默认为true
    public void setEnablePullToRefresh(boolean enable) {
        enablePullToRefresh = enable;
        if (enable == false) {
            enableLoadMoreData = false;
        }
    }

    public void setOnSwipeRefreshListener(OnSwipeRefreshLayoutListener listener) {
        refreshLayoutListener = listener;
    }

    public void setRefreshFinished() {
        isLoading = false;
        enableLoadMoreData = true;
        setRefreshing(false);
    }


    //设置需要刷新的View
    public void setRefreshView(View view) {
        if (view instanceof AbsListView) {
            setAbsListView((AbsListView) view);
        } else if (view instanceof RecyclerView) {
            setRecyclerView((RecyclerView) view);
        } else if (view instanceof CustomRecyclerView) {
            setRecyclerView(((CustomRecyclerView) view).getInnerRecyclerView());
        } else {
            throw new RuntimeException("The view type is not supported");
        }
    }

    protected void onFooterRefreshing() {
        isLoading = true;
        enableLoadMoreData = false;
        if (refreshLayoutListener != null) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    refreshLayoutListener.onFooterRefreshing();
                }
            }, 300);
        }
    }


    private void setAbsListView(AbsListView absListView) {
        absListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!enableLoadMoreData || isLoading || refreshLayoutListener == null) {
                    return;
                }

                if (!ViewCompat.canScrollVertically(view, 1)) {
                    onFooterRefreshing();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!enableLoadMoreData || isLoading || refreshLayoutListener == null) {
                    return;
                }
                if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                    onFooterRefreshing();
                }
            }
        });
    }

    public interface OnSwipeRefreshLayoutListener {
        void onHeaderRefreshing();
        void onFooterRefreshing();
    }
}
