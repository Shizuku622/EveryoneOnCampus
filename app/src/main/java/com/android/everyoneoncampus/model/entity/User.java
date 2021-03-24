package com.android.everyoneoncampus.model.entity;

import com.android.everyoneoncampus.EocApplication;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Blob;

public class User implements Serializable {
    public String userID = "";
    public String userPassword = "";  //密码
    public String userName = "";
    public String userSno = ""; //学号
    public String userPhone = "";
    public String userSex = "";
    public String userSchool = "";
    public String userPlace = "";
    public String userIdentity = "";
    public String userAutograph = "";
    public String userlabel = "";
    public String mark = "";
    public String userNicheng = "";
    public String dynamicNumber = "";
    public String followNumber = "";
    public String followedNumber = "";
    public String userSpeci = "";
    public byte[] headPic ;
    public String model;

    public User(){

    }

    public User(String uid, String uname, String usno, String uphone, String usex, String uschool, String uplace, String uiden, String uauto, String ulabel, String m, String unicheng, String dn, String fn, String fedn, String usp,byte[] hp){
        userID = uid;
        userName = uname;
        userSno = usno; //学号
        userPhone = uphone;
        userSex = usex;
        userSchool = uschool;
        userPlace = uplace;
        userIdentity = uiden;
        userAutograph = uauto;
        userlabel = ulabel;
        mark = m;
        userNicheng = unicheng;
        dynamicNumber = dn;
        followNumber = fn;
        followedNumber = fedn;
        userSpeci = usp;
        headPic = hp;
    }

    public User(String uid, String uname, String usno, String uphone, String usex, String uschool, String uplace, String uiden, String uauto, String ulabel, String m, String unicheng, String dn, String fn, String fedn, String usp,byte[] hp,String mdl){
        userID = uid;
        userName = uname;
        userSno = usno; //学号
        userPhone = uphone;
        userSex = usex;
        userSchool = uschool;
        userPlace = uplace;
        userIdentity = uiden;
        userAutograph = uauto;
        userlabel = ulabel;
        mark = m;
        userNicheng = unicheng;
        dynamicNumber = dn;
        followNumber = fn;
        followedNumber = fedn;
        userSpeci = usp;
        headPic = hp;
        model = mdl;
    }

}
