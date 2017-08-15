package com.cn.okhttp02;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends AppCompatActivity {
    public final static String TAG="reborn";
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        testApplicationInterceptors();
        testNetworkInterceptors();
    }
    private void init(){
        mContext=this;
    }


    //测试Application Interceptors
    public void testApplicationInterceptors(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.i(TAG,"----> run");
                    String url="http://blog.csdn.net/lfdfhl";
                    OkHttpClient.Builder okHttpClientBuilder= new OkHttpClient.Builder();
                    //添加应用拦截器
                    okHttpClientBuilder.addInterceptor(new LoggingInterceptor());
                    OkHttpClient okHttpClient=okHttpClientBuilder.build();
                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    requestBuilder.addHeader("os","Android");
                    requestBuilder.addHeader("country","China");
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG,"----> onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.i(TAG,"----> onResponse");
                            int code=response.code();
                            String result=response.body().string();
                            response.body().close();
                            Log.i(TAG,"测试Application Interceptors "+result);
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
    }


    //测试Network Interceptors
    public void testNetworkInterceptors(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.i(TAG,"----> run");
                    String url="http:www.github.com";
                    OkHttpClient.Builder okHttpClientBuilder= new OkHttpClient.Builder();
                    //添加网络拦截器
                    okHttpClientBuilder.addNetworkInterceptor(new LoggingInterceptor());
                    OkHttpClient okHttpClient=okHttpClientBuilder.build();
                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    requestBuilder.addHeader("os","Android");
                    requestBuilder.addHeader("country","China");
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG,"----> onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.i(TAG,"----> onResponse");
                            int code=response.code();
                            String result=response.body().string();
                            response.body().close();
                            Log.i(TAG,"测试Network Interceptors "+result);
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
    }


    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Log.i(TAG,"----> intercept");
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i(TAG, "intercept: " + String.format("Sending request %s on %s%n%s",request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i(TAG, "intercept: " + String.format("Received response for %s in %.1fms%n%s",response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }

}
