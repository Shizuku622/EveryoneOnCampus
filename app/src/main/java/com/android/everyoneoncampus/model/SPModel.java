package com.android.everyoneoncampus.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.everyoneoncampus.EocApplication;

public class SPModel {
    private SharedPreferences mUserInfoSP = EocApplication.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    private SharedPreferences mWriteInfoSP = EocApplication.getContext().getSharedPreferences("writeinfo",Context.MODE_PRIVATE);

    public final String QIANMING = "qianming";
    public final String NICHENG = "nicheng";
    public final String XINGMING = "xingming";
    public final String ZHUANYE = "zhuanye";

    private final String SAVEUSERINFO = "userSno";

    public SharedPreferences getWriteInfoSp(){
        return mWriteInfoSP;
    }

    public void saveUserInfo(String userID){
        SharedPreferences.Editor editor = mUserInfoSP.edit();
        editor.putString(SAVEUSERINFO,userID);
        editor.commit();
    }

    public String readUserInfo(){
        String userSno = mUserInfoSP.getString(SAVEUSERINFO,"0");
        return userSno;
    }
    //填写用户信息
    public void writeSex(String sex){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.putString("sex",sex);
        editor.commit();
    }
    public void writeIdent(String ident){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.putString("ident",ident);
        editor.commit();
    }
    public void writeQianMing(String qianming){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.putString(QIANMING,qianming);
        editor.commit();
    }
    public void writeXingMing(String xingming){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.putString(XINGMING,xingming);
        editor.commit();
    }
    public void writeNiCheng(String nicheng){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.putString(NICHENG,nicheng);
        editor.commit();
    }
    public void writeZhuanYe(String zhuanye){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.putString(ZHUANYE,zhuanye);
        editor.commit();
    }

//    public void writelabel(String label){
//        SharedPreferences.Editor editor = mWriteInfoSP.edit();
//        editor.putString("label",label);
//        editor.commit();
//    }

    public void clearSpEditor(){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.clear();
        editor.commit();
    }


    //查询是否已经填入
    public boolean infoSexIdent(){
        String ident = mWriteInfoSP.getString("ident","无");
        String sex = mWriteInfoSP.getString("sex","无");
        String qianming = mWriteInfoSP.getString(QIANMING,"无");
        String xingming = mWriteInfoSP.getString(XINGMING,"无");
        String nicheng = mWriteInfoSP.getString(NICHENG,"无");
        String zhuanye = mWriteInfoSP.getString(ZHUANYE,"无");

        if (zhuanye.equals("无") || ident.equals("无") || sex.equals("无") || qianming.equals("无") || xingming.equals("无") || nicheng.equals("无")) {
            return false;
        }
        return true;
    }



}
