package com.android.everyoneoncampus.model;

import com.autonavi.amap.mapcore.MsgProcessor;

import java.util.HashMap;
import java.util.Map;

public class ThingsConvert {
    private static Map<String,String> mStringMap = new HashMap<>();

    static {
        mStringMap.put("things","新鲜事");
        mStringMap.put("lose","丢失");
        mStringMap.put("take","拾到");
        mStringMap.put("problem","提问");
    }

    public static String convertString(String s){
        return mStringMap.get(s);
    }
}
