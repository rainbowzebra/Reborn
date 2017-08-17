package com.cn.recyclerview04;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayList;
/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class CustomRecyclerView extends FrameLayout {
    MyRecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FrameLayout emptyFrameLayout;

    private RecyclerView.AdapterDataObserver emptyObserver =
            new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    if (adapter == null) {
                        return;
                    }
                    if (adapter.getItemCount() <= 0) {
                        emptyFrameLayout.setVisibility(View.VISIBLE);
                    } else {
                        emptyFrameLayout.setVisibility(View.GONE);
                    }
                }
            };

    public CustomRecyclerView(Context context) {
        this(context, null);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int match_parent=ViewGroup.LayoutParams.MATCH_PARENT;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(match_parent,match_parent);
        emptyFrameLayout = new FrameLayout(getContext());
        recyclerView = new MyRecyclerView(getContext());
        addView(emptyFrameLayout, layoutParams);
        addView(recyclerView, layoutParams);
    }

    public void addEmpty(View emptyView) {
        emptyFrameLayout.removeAllViews();
        emptyFrameLayout.addView(emptyView);
    }

    public RecyclerView getInnerRecyclerView() {
        return recyclerView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            this.adapter = adapter;
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        recyclerView.setAdapter(adapter);
        emptyObserver.onChanged();
    }

    public void addHeaderView(View v) {
        recyclerView.addHeaderView(v);
    }

    public void removeHeaderView(View v) {
        recyclerView.removeHeaderView(v);
    }

    public void addFooterView(View v) {
        recyclerView.addFooterView(v);
    }

    public void removeFooterView(View v) {
        recyclerView.removeFooterView(v);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration, int index) {
        recyclerView.addItemDecoration(itemDecoration, index);
    }


    public void addOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener listener) {
        recyclerView.addOnChildAttachStateChangeListener(listener);
    }


    public void addOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        recyclerView.addOnItemTouchListener(listener);
    }


    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        recyclerView.addOnScrollListener(listener);
    }

    public void clearOnChildAttachStateChangeListeners() {
        recyclerView.clearOnChildAttachStateChangeListeners();
    }

    public void clearOnScrollListeners() {
        recyclerView.clearOnScrollListeners();
    }

    public View findChildViewUnder(float x, float y) {
        return recyclerView.findChildViewUnder(x, y);
    }

    @Nullable
    public View findContainingItemView(View view) {
        return recyclerView.findContainingItemView(view);
    }

    @Nullable
    public RecyclerView.ViewHolder findContainingViewHolder(View view) {
        return recyclerView.findContainingViewHolder(view);
    }

    public RecyclerView.ViewHolder findViewHolderForAdapterPosition(int position) {
        return recyclerView.findViewHolderForAdapterPosition(position);
    }

    public RecyclerView.ViewHolder findViewHolderForItemId(long id) {
        return recyclerView.findViewHolderForItemId(id);
    }

    public RecyclerView.ViewHolder findViewHolderForLayoutPosition(int position) {
        return recyclerView.findViewHolderForLayoutPosition(position);
    }

    public RecyclerView.ViewHolder findViewHolderForPosition(int position) {
        return recyclerView.findViewHolderForPosition(position);
    }

    public boolean fling(int velocityX, int velocityY) {
        return recyclerView.fling(velocityX, velocityY);
    }


    public RecyclerView.Adapter getAdapter() {
        return recyclerView.getAdapter();
    }

    public long getChildItemId(View childView) {
        return recyclerView.getChildItemId(childView);
    }

    public int getChildLayoutPosition(View childView) {
        return recyclerView.getChildLayoutPosition(childView);
    }

    public int getChildPosition(View childView) {
        return recyclerView.getChildPosition(childView);
    }

    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return recyclerView.getChildViewHolder(childView);
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return recyclerView.getCompatAccessibilityDelegate();
    }

    public RecyclerView.ItemAnimator getItemAnimator() {
        return recyclerView.getItemAnimator();
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return recyclerView.getLayoutManager();
    }

    public int getMaxFlingVelocity() {
        return recyclerView.getMaxFlingVelocity();
    }

    public int getMinFlingVelocity() {
        return recyclerView.getMinFlingVelocity();
    }

    public RecyclerView.RecycledViewPool getRecycledViewPool() {
        return recyclerView.getRecycledViewPool();
    }

    public int getScrollState() {
        return recyclerView.getScrollState();
    }

    public boolean hasFixedSize() {
        return recyclerView.hasFixedSize();
    }

    public boolean hasPendingAdapterUpdates() {
        return recyclerView.hasPendingAdapterUpdates();
    }

    public void invalidateItemDecorations() {
        recyclerView.invalidateItemDecorations();
    }

    public boolean isAnimating() {
        return recyclerView.isAnimating();
    }

    public boolean isComputingLayout() {
        return recyclerView.isComputingLayout();
    }

    public boolean isLayoutFrozen() {
        return recyclerView.isLayoutFrozen();
    }

    public void offsetChildrenHorizontal(int dx) {
        recyclerView.offsetChildrenHorizontal(dx);
    }

    public void offsetChildrenVertical(int dy) {
        recyclerView.offsetChildrenVertical(dy);
    }

    public void onChildAttachedToWindow(View child) {
        recyclerView.onChildAttachedToWindow(child);
    }

    public void onChildDetachedFromWindow(View child) {
        recyclerView.onChildDetachedFromWindow(child);
    }

    public void onScrolled(int dx, int dy) {
        recyclerView.onScrolled(dx, dy);
    }

    public void onScrollStateChanged(int state) {
        recyclerView.onScrollStateChanged(state);
    }

    public void removeItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.removeItemDecoration(itemDecoration);
    }

    public void removeOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener listener) {
        recyclerView.removeOnChildAttachStateChangeListener(listener);
    }

    public void removeOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        recyclerView.removeOnItemTouchListener(listener);
    }

    public void removeOnScrollListener(RecyclerView.OnScrollListener listener) {
        recyclerView.removeOnScrollListener(listener);
    }

    public void scrollToPosition(int position) {
        recyclerView.scrollToPosition(position);
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate accessibilityDelegate) {
        recyclerView.setAccessibilityDelegateCompat(accessibilityDelegate);
    }

    public void setChildDrawingOrderCallback(RecyclerView.ChildDrawingOrderCallback childDrawingOrderCallback) {
        recyclerView.setChildDrawingOrderCallback(childDrawingOrderCallback);
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        recyclerView.setHasFixedSize(hasFixedSize);
    }

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        recyclerView.setItemAnimator(itemAnimator);
    }


    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setRecycledViewPool(RecyclerView.RecycledViewPool pool) {
        recyclerView.setRecycledViewPool(pool);
    }

    public void setRecyclerListener(RecyclerView.RecyclerListener listener) {
        recyclerView.setRecyclerListener(listener);
    }

    public void setScrollingTouchSlop(int slopConstant) {
        recyclerView.setScrollingTouchSlop(slopConstant);
    }

    public void setViewCacheExtension(RecyclerView.ViewCacheExtension extension) {
        recyclerView.setViewCacheExtension(extension);
    }

    public void smoothScrollBy(int dx, int dy) {
        recyclerView.smoothScrollBy(dx, dy);
    }

    public void smoothScrollToPosition(int position) {
        recyclerView.smoothScrollToPosition(position);
    }

    public void stopScroll() {
        recyclerView.stopScroll();
    }

    public void swapAdapter(RecyclerView.Adapter adapter, boolean removeAndRecycleExistingViews) {
        recyclerView.swapAdapter(adapter, removeAndRecycleExistingViews);
    }


    private static class MyRecyclerView extends RecyclerView {
        private HeaderRecyclerAdapter headerRecyclerAdapter;

        public MyRecyclerView(Context context) {
            super(context);
        }

        public MyRecyclerView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public void setAdapter(Adapter adapter) {
            headerRecyclerAdapter = new HeaderRecyclerAdapter(adapter);
            super.setAdapter(headerRecyclerAdapter);
        }

        public void addHeaderView(View v) {
            if (headerRecyclerAdapter != null) {
                headerRecyclerAdapter.addHeaderView(v);
            }
        }

        public void removeHeaderView(View v) {
            if (headerRecyclerAdapter != null) {
                headerRecyclerAdapter.removeHeaderView(v);
            }
        }

        public void addFooterView(View v) {
            if (headerRecyclerAdapter != null) {
                headerRecyclerAdapter.addFooterView(v);
            }
        }

        public void removeFooterView(View v) {
            if (headerRecyclerAdapter != null) {
                headerRecyclerAdapter.removeFooterView(v);
            }
        }
    }


    private static class HeaderRecyclerAdapter
            <VH extends HeaderRecyclerAdapter.ViewHolder>
            extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_HEADER_VIEW = Integer.MIN_VALUE;
        private static final int TYPE_FOOTER_VIEW = Integer.MIN_VALUE + 1;

        //RecyclerView真正使用的Adapter
        private RecyclerView.Adapter<RecyclerView.ViewHolder> innerAdapter;

        private ArrayList<View> headerViewsArrayList = new ArrayList<>();
        private ArrayList<View> footerViewsArrayList = new ArrayList<>();

        private RecyclerView.AdapterDataObserver dataObserver = new RecyclerView.AdapterDataObserver() {

            @Override
            public void onChanged() {
                super.onChanged();
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                notifyItemRangeChanged(positionStart + getHeaderViewsCount(), itemCount);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                notifyItemRangeInserted(positionStart + getHeaderViewsCount(), itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                notifyItemRangeRemoved(positionStart + getHeaderViewsCount(), itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                int headerViewsCountCount = getHeaderViewsCount();
                notifyItemRangeChanged(fromPosition + headerViewsCountCount, toPosition + headerViewsCountCount + itemCount);
            }
        };

        public HeaderRecyclerAdapter() {
        }

        public HeaderRecyclerAdapter(RecyclerView.Adapter innerAdapter) {
            setAdapter(innerAdapter);
        }

        //设置Adapter
        public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {

            if (adapter != null) {
                if (!(adapter instanceof RecyclerView.Adapter))
                    throw new RuntimeException("your adapter must be a RecyclerView.Adapter");
            }

            if (innerAdapter != null) {
                notifyItemRangeRemoved(getHeaderViewsCount(), innerAdapter.getItemCount());
                innerAdapter.unregisterAdapterDataObserver(dataObserver);
            }

            this.innerAdapter = adapter;
            innerAdapter.registerAdapterDataObserver(dataObserver);
            notifyItemRangeInserted(getHeaderViewsCount(), innerAdapter.getItemCount());
        }

        public RecyclerView.Adapter getInnerAdapter() {
            return innerAdapter;
        }

        public void addHeaderView(View header) {

            if (header == null) {
                throw new RuntimeException("The header is null");
            }
            headerViewsArrayList.add(header);
            this.notifyDataSetChanged();
        }

        public void addFooterView(View footer) {
            if (footer == null) {
                throw new RuntimeException("The footer is null");
            }
            footerViewsArrayList.add(footer);
            this.notifyDataSetChanged();
        }


        //获取第一个footerView
        public View getFooterView() {
            return getFooterViewsCount() > 0 ? footerViewsArrayList.get(0) : null;
        }

        //获取第一个headerView
        public View getHeaderView() {
            return getHeaderViewsCount() > 0 ? headerViewsArrayList.get(0) : null;
        }

        public void removeHeaderView(View view) {
            headerViewsArrayList.remove(view);
            this.notifyDataSetChanged();
        }

        public void removeFooterView(View view) {
            footerViewsArrayList.remove(view);
            this.notifyDataSetChanged();
        }

        public int getHeaderViewsCount() {
            return headerViewsArrayList.size();
        }

        public int getFooterViewsCount() {
            return footerViewsArrayList.size();
        }

        public boolean isHeader(int position) {
            return getHeaderViewsCount() > 0 && position == 0;
        }

        public boolean isFooter(int position) {
            int lastPosition = getItemCount() - 1;
            return getFooterViewsCount() > 0 && position == lastPosition;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int headerViewsCountCount = getHeaderViewsCount();
            if (viewType < TYPE_HEADER_VIEW + headerViewsCountCount) {
                return new ViewHolder(headerViewsArrayList.get(viewType - TYPE_HEADER_VIEW));
            } else if (viewType >= TYPE_FOOTER_VIEW && viewType < Integer.MAX_VALUE / 2) {
                return new ViewHolder(footerViewsArrayList.get(viewType - TYPE_FOOTER_VIEW));
            } else {
                return innerAdapter.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int headerViewsCountCount = getHeaderViewsCount();
            if (position >= headerViewsCountCount &&
                    position < headerViewsCountCount + innerAdapter.getItemCount()) {
                innerAdapter.onBindViewHolder(holder, position - headerViewsCountCount);
            } else {
                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                    ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                }
            }
        }

        @Override
        public int getItemCount() {
            return getHeaderViewsCount() + getFooterViewsCount() + innerAdapter.getItemCount();
        }

        @Override
        public int getItemViewType(int position) {
            int innerCount = innerAdapter.getItemCount();
            int headerViewsCountCount = getHeaderViewsCount();
            if (position < headerViewsCountCount) {
                return TYPE_HEADER_VIEW + position;
            } else if (headerViewsCountCount <= position && position < headerViewsCountCount + innerCount) {

                int innerItemViewType = innerAdapter.getItemViewType(position - headerViewsCountCount);
                if (innerItemViewType >= Integer.MAX_VALUE / 2) {
                    throw new IllegalArgumentException
                            ("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
                }
                return innerItemViewType + Integer.MAX_VALUE / 2;
            } else {
                return TYPE_FOOTER_VIEW + position - headerViewsCountCount - innerCount;
            }
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
