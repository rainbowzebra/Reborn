package com.cn.retrofit01;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public interface GitHubService3 {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user,  @QueryMap Map<String, String> options);
}
