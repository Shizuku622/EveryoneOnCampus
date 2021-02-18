package com.android.everyoneoncampus.model;

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

    public static User getUser(String... info){
        User user = new User();
        user.userPassword = info[0];  //密码
        user.userName = info[1];
        user.userSno = info[2]; //学号
        user.userPhone = info[3];
        user.userSex = info[4];
        user.userSchool = info[5];
        user.userPlace = info[6];
        user.userIdentity = info[7];
        user.userIcon = info[8];
        user.userAutograph = info[9];
        user.userlabel = info[10];
        user.mark = info[11];
        user.userID = info[12];
        return user;
    }

}
