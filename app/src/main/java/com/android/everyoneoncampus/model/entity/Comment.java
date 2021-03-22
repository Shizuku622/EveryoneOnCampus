package com.android.everyoneoncampus.model.entity;

import android.service.quicksettings.TileService;

import org.w3c.dom.CDATASection;

public class Comment {
    public String CommentID = "";
    public String thingsID = "";
    public String userID = "";
    public String userNicheng = "";
    public String CContent = "";
    public String CDate = "";
    public byte[] headPic;

    public Comment(){

    }

    public Comment(String cid,String tid,String uid,String nicheng,String cc,String cd){
        CommentID = cid;
        thingsID = tid;
        userID = uid;
        userNicheng = nicheng;
        CContent = cc;
        CDate = cd;
    }

    public Comment(String cid,String tid,String uid,String nicheng,String cc,String cd,byte[] hp){
        CommentID = cid;
        thingsID = tid;
        userID = uid;
        userNicheng = nicheng;
        CContent = cc;
        CDate = cd;
        headPic = hp;
    }
}
