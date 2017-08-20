package com.cn.rxjava01;

import android.util.Log;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public class UserAccount implements User{

    private String name;

    public UserAccount(String name){
        this.name=name;
    }
    @Override
    public void receiveMessage(String message) {
        Log.i(Constant.TAG,name+"，收到消息："+message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
