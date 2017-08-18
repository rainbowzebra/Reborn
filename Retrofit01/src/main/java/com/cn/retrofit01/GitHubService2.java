package com.cn.retrofit01;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public interface GitHubService2 {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user,@Query("sort") String sort);
}
