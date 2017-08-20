package com.cn.rxjava01;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public interface Subscription {
    public void addUserAccount(UserAccount userAccount);
    public void deleteUserAccount(UserAccount userAccount);
    public void sendMessage(String message);
}
