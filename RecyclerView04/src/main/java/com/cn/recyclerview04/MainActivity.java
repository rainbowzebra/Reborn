package com.cn.recyclerview04;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private CustomRecyclerView mRecyclerView;
    private CustomSwipeRefreshLayout mCustomSwipeRefreshLayout;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private LinearLayout mLoadMoreLinearLayout;
    private List<String> mList;
    private Handler mHandler;
    public final static String TAG="reborn";
    public final static int REFRESH_FINISH = 9527;
    public final static int LOADMORE_FINISH = 9528;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        mContext=this;
        mRecyclerView= (CustomRecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(animator);
        DividerItemDecoration decoration=new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);
        mList=new ArrayList<String>();
        for(int i=0;i<20;i++){
            mList.add("Number is "+i);
        }
        mRecyclerViewAdapter=new RecyclerViewAdapter(mContext,mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        View headerView= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_header,null);
        mRecyclerView.addHeadView(headerView);
        mLoadMoreLinearLayout= (LinearLayout) findViewById(R.id.loadMoreLinearLayout);
        //View footerView= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_footer,null);
        //mRecyclerView.addFooterView(footerView);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"点击 "+position,Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerViewAdapter.setOnItemLongClickListener(new RecyclerViewAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext,"长按 "+position,Toast.LENGTH_SHORT).show();
                mRecyclerViewAdapter.deleteItem(position);
            }
        });

        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==REFRESH_FINISH){
                    Log.i(TAG,"finish onHeaderRefreshing");
                    mCustomSwipeRefreshLayout.setRefreshFinished();
                }

                if(msg.what==LOADMORE_FINISH){
                    mLoadMoreLinearLayout.setVisibility(View.GONE);
                    mCustomSwipeRefreshLayout.setRefreshFinished();
                    mRecyclerViewAdapter.notifyDataSetChanged();
                    Log.i(TAG,"finish onFooterRefreshing");
                }
            }
        };

        mCustomSwipeRefreshLayout= (CustomSwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mCustomSwipeRefreshLayout.setRefreshView(mRecyclerView);
        mCustomSwipeRefreshLayout.setFooterRefreshAble(true);
        mCustomSwipeRefreshLayout.setOnSwipeRefreshListener(new CustomSwipeRefreshLayout.OnSwipeRefreshLayoutListener() {
            @Override
            public void onHeaderRefreshing() {
                Log.i(TAG,"start onHeaderRefreshing");
                mHandler.sendEmptyMessageDelayed(REFRESH_FINISH,1000*5);
            }

            @Override
            public void onFooterRefreshing() {
                Log.i(TAG,"start onFooterRefreshing");
                mLoadMoreLinearLayout.setVisibility(View.VISIBLE);
                for(int i=0;i<20;i++){
                    mList.add("load more number is "+i);
                }
                mHandler.sendEmptyMessageDelayed(LOADMORE_FINISH,1000*5);
            }
        });
    }
}
