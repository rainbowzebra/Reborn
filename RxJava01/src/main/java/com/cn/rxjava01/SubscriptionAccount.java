package com.cn.rxjava01;

import android.text.TextUtils;
import java.util.ArrayList;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public class SubscriptionAccount implements Subscription{
    private ArrayList<UserAccount> userAccountArrayList;

    public SubscriptionAccount(){
        userAccountArrayList=new ArrayList<>();
    }

    @Override
    public void addUserAccount(UserAccount userAccount) {
        if (userAccount!=null){
            userAccountArrayList.add(userAccount);
        }
    }

    @Override
    public void deleteUserAccount(UserAccount userAccount) {
        if (userAccount!=null){
            userAccountArrayList.remove(userAccount);
        }
    }

    @Override
    public void sendMessage(String message) {
        if(!TextUtils.isEmpty(message)){
            for(UserAccount userAccount:userAccountArrayList){
                userAccount.receiveMessage(message);
            }
        }
    }
}

