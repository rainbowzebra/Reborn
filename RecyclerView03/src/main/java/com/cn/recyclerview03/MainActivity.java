package com.cn.recyclerview03;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends AppCompatActivity {
    public final static String TAG="reborn";
    public final static int DOWNLOAD_FINISH=9527;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<Girl> mList;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getData();
    }
    private void init(){
        mContext=this;
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,3,VERTICAL,false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(animator);
        mList=new ArrayList<Girl>();
        mRecyclerViewAdapter=new RecyclerViewAdapter(mContext,mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
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
                if(msg.what==DOWNLOAD_FINISH){
                    mRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };
    }

    public void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result=response.body().string();
                            Result res=JSON.parseObject(result,Result.class);
                            response.body().close();
                            List<Girl> girlsList=res.getResults();
                            mList.addAll(girlsList);
                            Message message=new Message();
                            message.what=DOWNLOAD_FINISH;
                            mHandler.sendMessage(message);
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
    }
}
