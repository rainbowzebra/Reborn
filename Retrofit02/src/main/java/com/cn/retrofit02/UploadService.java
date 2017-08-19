package com.cn.retrofit02;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public interface UploadService {
    @Multipart
    @POST("SpringMVC07/testUpload/uploadFile.do")
    Call<String> uploadFile(@Part("description") RequestBody descriptionRequestBody,
                            @Part MultipartBody.Part multipartBodyPart);
}
