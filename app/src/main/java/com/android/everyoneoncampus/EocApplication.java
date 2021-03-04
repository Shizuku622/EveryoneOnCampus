package com.android.everyoneoncampus;

import android.app.Application;
import android.content.Context;

import com.mysql.fabric.Server;

import cn.leancloud.AVOSCloud;
import cn.leancloud.chatkit.LCChatKit;

public class EocApplication extends Application {
    private static Context context;
    private static String userID;

    private final String APP_ID = "LmuXlj2lu5JzK1ynPyKwngml-gzGzoHsz";
    private final String APP_KEY = "v4njrqhhEREfqnKj1V3VNyVT";
    private final String SERVER_URL = "https://lmuxlj2l.lc-cn-n1-shared.com";

    //设置ui
    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        EocApplication.userID = userID;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //leancloud init 初始化工作
        AVOSCloud.initialize(this, APP_ID, APP_KEY, SERVER_URL);

    }



    public static Context getContext(){
        return context;
    }
}
