package com.android.everyoneoncampus.model;

import com.android.everyoneoncampus.EocApplication;

public class User {
    public String userID;
    public String userPassword;  //密码
    public String userName;
    public String userSno; //学号
    public String userPhone;
    public String userSex;
    public String userSchool;
    public String userPlace;
    public String userIdentity;
    public String userIcon;
    public String userAutograph;
    public String userlabel;
    public String mark;
    public String userNicheng;
    public String dynamicNumber;
    public String followNumber;
    public String followedNumber;

    public static User getUser(String... info){
        EocApplication.EocUser = new User();
        EocApplication.EocUser.userPassword = info[0];  //密码
        EocApplication.EocUser.userName = info[1];
        EocApplication.EocUser.userSno = info[2]; //学号
        EocApplication.EocUser.userPhone = info[3];
        EocApplication.EocUser.userSex = info[4];
        EocApplication.EocUser.userSchool = info[5];
        EocApplication.EocUser.userPlace = info[6];
        EocApplication.EocUser.userIdentity = info[7];
        EocApplication.EocUser.userIcon = info[8];
        EocApplication.EocUser.userAutograph = info[9];
        EocApplication.EocUser.userlabel = info[10];
        EocApplication.EocUser.mark = info[11];
        EocApplication.EocUser.userID = info[12];
        EocApplication.EocUser.userNicheng = info[13];
        EocApplication.EocUser.dynamicNumber = info[14];
        EocApplication.EocUser.followNumber = info[15];
        EocApplication.EocUser.followedNumber = info[16];
        return  EocApplication.EocUser;
    }

}
