package com.cn.recyclerview02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<String> mList;
    private onItemClickListener mOnItemClickListener;
    private onItemLongClickListener mOnItemLongClickListener;

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(itemView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    int position=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.textView,position);
                }
            }
        });
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position=holder.getLayoutPosition();
                mOnItemLongClickListener.onItemLongClick(holder.textView,position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }else {
            return 0;
        }
    }

    public void deleteItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface onItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.mOnItemClickListener=listener;
    }

    public void setOnItemLongClickListener(onItemLongClickListener listener){
        this.mOnItemLongClickListener=listener;
    }

}