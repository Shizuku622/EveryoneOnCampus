package com.android.everyoneoncampus;

import android.app.Application;
import android.content.Context;

import com.android.everyoneoncampus.model.User;
import com.mysql.fabric.Server;

import cn.leancloud.AVOSCloud;
import cn.leancloud.chatkit.LCChatKit;

public class EocApplication extends Application {
    private static Context context;
    private static String userID;

    private final String APP_ID = "LmuXlj2lu5JzK1ynPyKwngml-gzGzoHsz";
    private final String APP_KEY = "v4njrqhhEREfqnKj1V3VNyVT";
    private final String SERVER_URL = "https://lmuxlj2l.lc-cn-n1-shared.com";

    private static User EocUser = new User();

    //设置ui
    public static User getUserInfo() {
        return EocUser;
    }

    public static void setUserInfo(User user){
        EocUser.userID = user.userID;
        EocUser.userPassword= user.userPassword;
        EocUser.userName= user.userName;
        EocUser.userSno= user.userSno;
        EocUser.userPhone= user.userPhone;
        EocUser.userSex= user.userSex;
        EocUser.userSchool= user.userSchool;
        EocUser.userPlace= user.userPlace;
        EocUser.userIdentity= user.userIdentity;
        EocUser.userIcon= user.userIcon;
        EocUser.userAutograph= user.userAutograph;
        EocUser.userlabel= user.userlabel;
        EocUser.mark= user.mark;
        EocUser.userNicheng= user.userNicheng;
        EocUser.dynamicNumber= user.dynamicNumber;
        EocUser.followNumber= user.followNumber;
        EocUser.followedNumber= user.followedNumber;
        EocUser.userSpeci= user.userSpeci;
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
