package com.cn.retrofit02;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
    private static final int REQUEST_CODE = 9527;
    private static String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mContext=this;
        verifyStoragePermissions(this);
    }


    public void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST_CODE);
        }else {
            testRetrofit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    testRetrofit();
                } else {
                    Toast.makeText(mContext, "您未授权存储权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }


    private void testRetrofit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                testUpload();
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

    //上传
    public void testUpload(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http:192.168.0.103:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //待上传的文件
        String rootPath=Environment.getExternalStorageDirectory().getAbsolutePath();
        File file=new File(rootPath+File.separator+"test.jpg");
        String fileName=file.getName();
        //描述信息
        String description = "This is description";
        MediaType descriptionMediaType=MediaType.parse("multipart/form-data");
        RequestBody descriptionRequestBody = RequestBody.create(descriptionMediaType, description);
        //文件
        MediaType fileMediaType=MediaType.parse("multipart/form-data");
        RequestBody fileRequestBody = RequestBody.create(fileMediaType, file);
        MultipartBody.Part multipartBodyPart = MultipartBody.Part.createFormData("image", fileName, fileRequestBody);
        //上传
        UploadService uploadService=retrofit.create(UploadService.class);
        Call<String> call = uploadService.uploadFile(descriptionRequestBody, multipartBodyPart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG,"onResponse");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(TAG,"onFailure");
            }
        });
    }

}
