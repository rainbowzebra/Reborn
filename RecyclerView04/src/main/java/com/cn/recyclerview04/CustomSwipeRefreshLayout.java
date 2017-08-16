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

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    protected boolean footerRefreshAble;
    private boolean enableRefresh = true;  // 是否可以下拉刷新.

    private boolean loading = false;  // 是否在加载...

    private OnSwipeRefreshLayoutListener refreshLayoutListener;
    private Handler handler;

    public CustomSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        setColorSchemeColors(Color.parseColor("#62A8DB"), Color.RED);
        footerRefreshAble = false;
        handler = new Handler();
        setOnRefreshListener(this);
    }


    @Override
    public boolean isEnabled() {
        if (!enableRefresh) {
            return false;
        }

        if (loading) {
            return false;
        }

        return super.isEnabled();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (loading) {
            return;
        }

        if (refreshing && isRefreshing() != refreshing) {
            onRefresh();
        }
        super.setRefreshing(refreshing);
    }

    @Override
    public void onRefresh() {
        loading = true;
        if (refreshLayoutListener != null) {
            refreshLayoutListener.onHeaderRefreshing();
        }
    }

    /**
     * 是否可以上拉
     *
     * @param able true 为可以
     */
    public void setFooterRefreshAble(boolean able) {
        if (!enableRefresh)
            return;
        footerRefreshAble = able;
    }

    /**
     * 设置刷新是否能使用
     *
     * @param enable true 能使用, 默认为true
     */
    public void setEnableRefresh(boolean enable) {
        enableRefresh = enable;
        if (enable == false) {
            footerRefreshAble = false;
        }
    }

    public void setOnSwipeRefreshListener(OnSwipeRefreshLayoutListener listener) {
        refreshLayoutListener = listener;
    }

    public void setRefreshFinished() {
        loading = false;
        footerRefreshAble = true;
        setRefreshing(false);
    }

    /**
     * 设置上拉加载更多的View
     *
     * @param _view 支持(AbsListView、recyclerView、CustomRecyclerView)
     */
    public void setRefreshView(View _view) {
        if (_view instanceof AbsListView) {
            setAbsListView((AbsListView) _view);
        } else if (_view instanceof RecyclerView) {
            setRecyclerView((RecyclerView) _view);
        } else if (_view instanceof CustomRecyclerView) {
            setRecyclerView(((CustomRecyclerView) _view).getInnerRecyclerView());
        } else {
            throw new RuntimeException("View type is not supported");
        }
    }

    protected void onFooterRefreshing() {
        loading = true;
        footerRefreshAble = false;
        if (refreshLayoutListener != null) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    refreshLayoutListener.onFooterRefreshing();
                }
            }, 300);
        }
    }


    private void setAbsListView(AbsListView _lv) {
        _lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!footerRefreshAble || loading || refreshLayoutListener == null) {
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

    private void setRecyclerView(RecyclerView _rv) {
        _rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!footerRefreshAble || loading || refreshLayoutListener == null) {
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
