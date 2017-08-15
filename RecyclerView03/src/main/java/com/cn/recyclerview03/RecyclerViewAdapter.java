package com.cn.recyclerview03;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<Girl> mList;
    private onItemClickListener mOnItemClickListener;
    private onItemLongClickListener mOnItemLongClickListener;
    public final static String TAG="reborn";

    public RecyclerViewAdapter(Context context, List<Girl> list) {
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
        Glide.with(mContext).load(mList.get(position).getUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    int position=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.imageView,position);
                }
            }
        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position=holder.getLayoutPosition();
                mOnItemLongClickListener.onItemLongClick(holder.imageView,position);
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
        private ImageView imageView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public interface onItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface onItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.mOnItemClickListener=listener;
    }

    public void setOnItemLongClickListener(onItemLongClickListener listener){
        this.mOnItemLongClickListener=listener;
    }

}