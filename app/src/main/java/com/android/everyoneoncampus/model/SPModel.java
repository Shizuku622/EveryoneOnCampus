package com.android.everyoneoncampus.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.everyoneoncampus.EocApplication;

public class SPModel {
    private SharedPreferences mUserInfoSP = EocApplication.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    private SharedPreferences mWriteInfoSP = EocApplication.getContext().getSharedPreferences("writeinfo",Context.MODE_PRIVATE);


    public void saveUserInfo(User user){
        SharedPreferences.Editor editor = mUserInfoSP.edit();
        editor.putInt("userID",user.userID);
        editor.putString("userPassword",user.userPassword);
        editor.putString("userName",user.userName);
        editor.putString("userSno",user.userSno);
        editor.putString("userSex",user.userSex);
        editor.putString("userSchool",user.userSchool);
        editor.putString("userPlace",user.userPlace);
        editor.putString("userIdentity",user.userIdentity);
        editor.putString("userIcon",user.userIcon);
        editor.putString("userAutograph",user.userAutograph);
        editor.putString("userLabel",user.userLabel);
        editor.putString("userPhone",user.userPhone);
        editor.putInt("mark",user.mark);
        editor.commit();
    }

    public User readUserInfo(){
        User user = new User();
        user.userID = mUserInfoSP.getInt("userID",0);
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
        user.userLabel = mUserInfoSP.getString("userLabel","");
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




}
