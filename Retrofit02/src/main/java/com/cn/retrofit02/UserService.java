package com.cn.retrofit02;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public interface UserService {
    @POST("SpringMVC06/json/requestJson.do")
    Call<User> findIPInfo(@Body User user);
}
