package com.cn.retrofit01;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResult> login1(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResult> login2(@FieldMap Map<String,String> map);


}
