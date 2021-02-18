package com.android.everyoneoncampus;

import android.app.Application;
import android.content.Context;

public class EocApplication extends Application {
    private static Context context;
    private static String userID;

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
    }



    public static Context getContext(){
        return context;
    }
}
