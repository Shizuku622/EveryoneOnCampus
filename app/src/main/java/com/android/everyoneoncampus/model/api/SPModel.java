package com.android.everyoneoncampus.model.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.everyoneoncampus.EocApplication;

public class SPModel {
    private SharedPreferences mUserInfo = EocApplication.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    private SharedPreferences mWriteInfo = EocApplication.getContext().getSharedPreferences("writeinfo",Context.MODE_PRIVATE);

    public final String QIAN_MING = "QIANMING";
    public final String NI_CHENG = "NICHENG";
    public final String XING_MING = "XINGMING";
    public final String ZHUAN_YE = "ZHUANYE";
    private final String SAVE_USER_ID = "USERID";
    public static final String NO_INFO = "NOUSER";


    public SharedPreferences getWriteInfoSp(){
        return mWriteInfo;
    }

    //设置重新登录之后，之后获取最新的。

    //清除用户ID
    public void clearUserID(){
        SharedPreferences.Editor editor = mUserInfo.edit();
        editor.clear();
        editor.commit();
    }
    //保存用户ID
    public void saveUserID(String userID){
        SharedPreferences.Editor editor = mUserInfo.edit();
        editor.putString(SAVE_USER_ID,userID);
        editor.commit();
    }
    //读取用户ID
    public String readUserID(){
        String userID = mUserInfo.getString(SAVE_USER_ID,NO_INFO);
        return userID;
    }

    //填写用户信息
    public void writeSex(String sex){
        SharedPreferences.Editor editor = mWriteInfo.edit();
        editor.putString("sex",sex);
        editor.commit();
    }
    public void writeIdent(String ident){
        SharedPreferences.Editor editor = mWriteInfo.edit();
        editor.putString("ident",ident);
        editor.commit();
    }
    public void writeQianMing(String qianming){
        SharedPreferences.Editor editor = mWriteInfo.edit();
        editor.putString(QIAN_MING,qianming);
        editor.commit();
    }
    public void writeXingMing(String xingming){
        SharedPreferences.Editor editor = mWriteInfo.edit();
        editor.putString(XING_MING,xingming);
        editor.commit();
    }
    public void writeNiCheng(String nicheng){
        SharedPreferences.Editor editor = mWriteInfo.edit();
        editor.putString(NI_CHENG,nicheng);
        editor.commit();
    }
    public void writeZhuanYe(String zhuanye){
        SharedPreferences.Editor editor = mWriteInfo.edit();
        editor.putString(ZHUAN_YE,zhuanye);
        editor.commit();
    }


    public void clearSpEditor(){
        SharedPreferences.Editor editor = mWriteInfo.edit();
        editor.clear();
        editor.commit();
    }


    public boolean infoSexIdent(){
        String ident = mWriteInfo.getString("ident","无");
        String sex = mWriteInfo.getString("sex","无");
        String qianming = mWriteInfo.getString(QIAN_MING,"无");
        String xingming = mWriteInfo.getString(XING_MING,"无");
        String nicheng = mWriteInfo.getString(NI_CHENG,"无");
        String zhuanye = mWriteInfo.getString(ZHUAN_YE,"无");

        if (zhuanye.equals("无") || ident.equals("无") || sex.equals("无") || qianming.equals("无") || xingming.equals("无") || nicheng.equals("无")) {
            return false;
        }
        return true;
    }



}
