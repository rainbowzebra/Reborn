package com.cn.retrofit02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
                testPostJson();
            }
        }).start();
    }


    //将参数以JSON格式提交至服务器
    public void testPostJson(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http:192.168.0.103:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService=retrofit.create(UserService.class);
        User user=new User();
        user.setId(9527);
        user.setUsername("zxc");
        user.setSex("man");
        user.setAddress("China");
        Call<User> call= userService.findIPInfo(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(TAG,"onResponse "+response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });

    }

}
