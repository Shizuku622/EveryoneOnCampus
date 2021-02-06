package com.android.everyoneoncampus.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.everyoneoncampus.EocApplication;

public class SPModel {
    private SharedPreferences mUserInfoSP = EocApplication.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    private SharedPreferences mWriteInfoSP = EocApplication.getContext().getSharedPreferences("writeinfo",Context.MODE_PRIVATE);


    public SharedPreferences getSp(){
        return mWriteInfoSP;
    }

    public void saveUserInfo(String userSno){
        SharedPreferences.Editor editor = mUserInfoSP.edit();
        editor.putString("usersno",userSno);
        editor.commit();
    }

    public String readUserInfo(){
        String userSno = mUserInfoSP.getString("usersno","0");
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

    public void writelabel(String label){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.putString("label",label);
        editor.commit();
    }

    public void clearSpEditor(){
        SharedPreferences.Editor editor = mWriteInfoSP.edit();
        editor.clear();
        editor.commit();
    }


    //查询是否已经填入
    public boolean infoSexIdent(){
        String ident = mWriteInfoSP.getString("ident","无");
        String sex = mWriteInfoSP.getString("sex","无");
        if (ident.equals("无") || sex.equals("无")) {
            return false;
        }
        return true;
    }



}
