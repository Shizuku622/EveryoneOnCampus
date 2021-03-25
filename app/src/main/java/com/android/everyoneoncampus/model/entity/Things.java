package com.android.everyoneoncampus.model.entity;

import java.io.Serializable;

public class Things implements Serializable {
    public String thingsID = "";
    public String userID  = "";
    public String userNicheng  = "";
    public String event  = "";
    public String thingsContent = "";
    public String thingsDate = "";
    public byte[] Thingsimage;
    public byte[] headPic;
    public String commentNum = "";
    public String likeNum = "";
    public String likeMark = "";
    public String commentMark = "";

    public Things(){

    }

    public Things(String id,String event,String date,String content){
        this.userID = id;
        this.thingsDate = date;
        this.thingsContent = content;
        this.event = event;
    }

    public Things(String tid,String uid,String unicheng,String e,String tcontent,String tdate,byte[] img,byte[] hpic){
        this.thingsID = tid;
        this.userID = uid;
        this.userNicheng = unicheng;
        this.event = e;
        this.thingsContent = tcontent;
        this.thingsDate = tdate;
        this.Thingsimage = img;
        this.headPic = hpic;
    }

    public Things(String tid,String uid,String unicheng,String e,String tcontent,String tdate,byte[] img,byte[] hpic,String cnum,String like){
        this.thingsID = tid;
        this.userID = uid;
        this.userNicheng = unicheng;
        this.event = e;
        this.thingsContent = tcontent;
        this.thingsDate = tdate;
        this.Thingsimage = img;
        this.headPic = hpic;
        this.commentNum = cnum;
        this.likeNum = like;
    }

    public Things(String tid, String uid, String unicheng, String e, String tcontent, String tdate, byte[] img, byte[] hpic, String cnum, String like, String lm, String cm){
        this.thingsID = tid;
        this.userID = uid;
        this.userNicheng = unicheng;
        this.event = e;
        this.thingsContent = tcontent;
        this.thingsDate = tdate;
        this.Thingsimage = img;
        this.headPic = hpic;
        this.commentNum = cnum;
        this.likeNum = like;
        this.likeMark = lm;
        this.commentMark = cm;
    }

}
