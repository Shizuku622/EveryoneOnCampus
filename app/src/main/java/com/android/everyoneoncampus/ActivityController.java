package com.android.everyoneoncampus;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityController {
    private static List<Activity> mActivityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        mActivityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        mActivityList.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity : mActivityList){
            if(!activity.isFinishing()){//没有怎么在销毁
                activity.finish();
            }
        }
    }



}
