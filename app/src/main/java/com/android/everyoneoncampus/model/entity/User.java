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
    public String userIcon = "";
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

    public User(String uid, String uname, String usno, String uphone, String usex, String uschool, String uplace, String uiden, String uicon, String uauto, String ulabel, String m, String unicheng, String dn, String fn, String fedn, String usp,byte[] hp){
        userID = uid;
        userName = uname;
        userSno = usno; //学号
        userPhone = uphone;
        userSex = usex;
        userSchool = uschool;
        userPlace = uplace;
        userIdentity = uiden;
        userIcon = uicon;
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

    public User(String uid,String upass, String uname, String usno, String uphone, String usex, String uschool, String uplace, String uiden, String uicon, String uauto, String ulabel, String m, String unicheng, String dn, String fn, String fedn, String usp,byte[] hp,String mdl){
        userID = uid;
        userPassword = upass;  //密码
        userName = uname;
        userSno = usno; //学号
        userPhone = uphone;
        userSex = usex;
        userSchool = uschool;
        userPlace = uplace;
        userIdentity = uiden;
        userIcon = uicon;
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


//    public static User getUser(String... info){
//        EocApplication.EocUser = new User();
//        EocApplication.EocUser.userPassword = info[0];  //密码
//        EocApplication.EocUser.userName = info[1];
//        EocApplication.EocUser.userSno = info[2]; //学号
//        EocApplication.EocUser.userPhone = info[3];
//        EocApplication.EocUser.userSex = info[4];
//        EocApplication.EocUser.userSchool = info[5];
//        EocApplication.EocUser.userPlace = info[6];
//        EocApplication.EocUser.userIdentity = info[7];
//        EocApplication.EocUser.userIcon = info[8];
//        EocApplication.EocUser.userAutograph = info[9];
//        EocApplication.EocUser.userlabel = info[10];
//        EocApplication.EocUser.mark = info[11];
//        EocApplication.EocUser.userID = info[12];
//        EocApplication.EocUser.userNicheng = info[13];
//        EocApplication.EocUser.dynamicNumber = info[14];
//        EocApplication.EocUser.followNumber = info[15];
//        EocApplication.EocUser.followedNumber = info[16];
//        EocApplication.EocUser.userSpeci = info[17];
//        return  EocApplication.EocUser;
//    }

}
