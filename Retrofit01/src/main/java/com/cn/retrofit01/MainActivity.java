package com.cn.retrofit01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends AppCompatActivity {
    public final static String TAG="reborn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testRetrofit();
    }

    private void testRetrofit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                test1();
            }
        }).start();
    }

    //简单的Get请求
    //https://api.github.com/users/octocat/repos
    public void test1(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService1 gitHubService=retrofit.create(GitHubService1.class);
        Call<List<Repo>> call=gitHubService.listRepos("octocat");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i(TAG,"onResponse");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }

    //带参的Get请求
    //https://api.github.com/users/octocat/repos
    public void test2(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService1 gitHubService=retrofit.create(GitHubService1.class);
        Call<List<Repo>> call=gitHubService.listRepos("octocat");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i(TAG,"onResponse");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }


}
