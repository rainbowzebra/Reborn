package com.cn.okhttp01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
        //testGet1();
        //testGet2();
        testPost1();
        //testPost2();
        //testDownload();
        //testUpload();
    }
    private void init(){
        mContext=this;
    }

    //Get同步请求
    public void testGet1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://blog.csdn.net/lfdfhl";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    Response response=call.execute();
                    String result=response.body().string();
                    response.body().close();
                    Log.i(TAG,"测试Get同步请求 "+result);
                }catch (Exception e){

                }
            }
        }).start();
    }

    //Get异步请求
    public void testGet2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://blog.csdn.net/lfdfhl";
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
                            int code=response.code();
                            String result=response.body().string();
                            response.body().close();
                            Log.i(TAG,"code="+code);
                            Log.i(TAG,"测试Get异步请求 "+result);
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
    }


    //POST同步请求
    public void testPost1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://106.14.136.52:8080/user/login";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request.Builder requestBuilder=new Request.Builder();
                    FormBody.Builder formBodyBuilder=new FormBody.Builder();
                    formBodyBuilder.add("username","andy");
                    formBodyBuilder.add("password","123456");
                    RequestBody requestBody=formBodyBuilder.build();
                    requestBuilder.url(url);
                    requestBuilder.post(requestBody);
                    requestBuilder.addHeader("apikey","81bf9da930c7f9825a3c3383f1d8d766");
                    Request request=requestBuilder.build();
                    Response response=okHttpClient.newCall(request).execute();
                    int code=response.code();
                    String result=response.body().string();
                    response.body().close();
                    Log.i(TAG,"code="+code);
                    Log.i(TAG,"测试Post同步请求 "+result);
                }catch (Exception e){

                }
            }
        }).start();
    }


    //POST异步请求
    public void testPost2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://106.14.136.52:8080/user/login";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request.Builder requestBuilder=new Request.Builder();
                    FormBody.Builder formBodyBuilder=new FormBody.Builder();
                    formBodyBuilder.add("username","andy");
                    formBodyBuilder.add("password","123456");
                    RequestBody requestBody=formBodyBuilder.build();
                    requestBuilder.url(url);
                    requestBuilder.post(requestBody);
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            int code=response.code();
                            String result=response.body().string();
                            response.body().close();
                            Log.i(TAG,"code="+code);
                            Log.i(TAG,"测试Post异步请求 "+result);
                        }
                    });

                }catch (Exception e){

                }
            }
        }).start();
    }

    //下载
    public void testDownload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://img.blog.csdn.net/20161023140032428";
                    final File downloadFile=new File(getExternalCacheDir().toString()+File.separator+"test.jpg");
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
                            if(response.isSuccessful()){
                                InputStream inputStream=response.body().byteStream();
                                FileOutputStream fileOutputStream=new FileOutputStream(downloadFile);
                                int len=0;
                                byte b []=new byte[1024*2];
                                while ((len=inputStream.read(b))!=-1){
                                    fileOutputStream.write(b,0,len);
                                }
                                fileOutputStream.close();
                                inputStream.close();
                            }
                            response.body().close();
                            Bitmap bitmap = BitmapFactory.decodeFile(downloadFile.getAbsolutePath());
                            Log.i(TAG,"width="+bitmap.getWidth());
                            Log.i(TAG,"height="+bitmap.getHeight());
                        }
                    });

                }catch (Exception e){

                }
            }
        }).start();
    }


    //上传
    public void testUpload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url ="you url";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    MultipartBody.Builder multipartBodyBuilder=new MultipartBody.Builder();
                    //添加文件
                    File file=new File("");
                    String fileName=file.getName();
                    MediaType mediaType=MediaType.parse("image/*");
                    RequestBody requestBody=RequestBody.create(mediaType,file);
                    multipartBodyBuilder.addFormDataPart("photo",fileName,requestBody);

                    //添加参数
                    multipartBodyBuilder.addFormDataPart("name","zxx");
                    multipartBodyBuilder.addFormDataPart("number","9527");
                    multipartBodyBuilder.addFormDataPart("country","China");

                    MultipartBody multipartBody=multipartBodyBuilder.build();

                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    requestBuilder.post(multipartBody);

                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG,"onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.i(TAG,"onResponse");
                        }
                    });

                }catch (Exception e){

                }
            }
        }).start();
    }

}
