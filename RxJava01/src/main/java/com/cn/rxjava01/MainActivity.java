package com.cn.rxjava01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        SubscriptionAccount subscriptionAccount=new SubscriptionAccount();
        UserAccount userAccount1=new UserAccount("老王");
        UserAccount userAccount2=new UserAccount("小弟");
        UserAccount userAccount3=new UserAccount("包包");
        subscriptionAccount.addUserAccount(userAccount1);
        subscriptionAccount.addUserAccount(userAccount2);
        subscriptionAccount.addUserAccount(userAccount3);
        subscriptionAccount.sendMessage("有心课堂新课程上线啦");
    }
}
