package com.android.everyoneoncampus.model.entity;

import android.provider.UserDictionary;

import java.io.Serializable;

public class Things implements Serializable {
    public String thingsID;
    public String userID;
    public String userNicheng;
    public String event;
    public String thingsContent;
    public String thingsDate;
    public byte[] image;
    public byte[] headPic;

    public Things(){

    }

    public Things(String id,String event,String date,String content){
        userID = id;
        thingsDate = date;
        thingsContent = content;
        event = event;
    }

    public Things(String tid,String uid,String unicheng,String e,String tcontent,String tdate,byte[] img,byte[] hpic){
        thingsID = tid;
        userID = uid;
        userNicheng = unicheng;
        event = e;
        thingsContent = tcontent;
        thingsDate = tdate;
        image = img;
        headPic = hpic;
    }

}
