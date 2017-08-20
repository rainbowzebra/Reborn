package com.cn.rxjava02;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
