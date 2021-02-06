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

    public void saveUserInfo(User user){
        SharedPreferences.Editor editor = mUserInfoSP.edit();
        editor.putString("userSno",user.userSno);
        editor.putInt("mark",user.mark);
        editor.commit();
    }

    public User readUserInfo(){
        User user = new User();
        user.userPassword = mUserInfoSP.getString("userPassword","");
        user.userName = mUserInfoSP.getString("userName","");
        user.userSno = mUserInfoSP.getString("userSno","");
        user.userPhone = mUserInfoSP.getString("userPhone","");
        user.userSex = mUserInfoSP.getString("userSex","");
        user.userSchool = mUserInfoSP.getString("userSchool","");
        user.userPlace = mUserInfoSP.getString("userPlace","");
        user.userIdentity = mUserInfoSP.getString("userIdentity","");
        user.userIcon = mUserInfoSP.getString("userIcon","");
        user.userAutograph = mUserInfoSP.getString("userAutograph","");
        user.userlabel = mUserInfoSP.getString("userLabel","");
        user.mark = mUserInfoSP.getInt("mark",0);
        return user;
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
