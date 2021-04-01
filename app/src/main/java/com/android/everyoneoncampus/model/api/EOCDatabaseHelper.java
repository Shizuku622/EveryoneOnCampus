package com.android.everyoneoncampus.model.api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class EOCDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "EOCDatabaseHelper";
    public EOCDatabaseHelper(@Nullable Context context, @Nullable String name,  int version) {
        super(context, name, null, version);
    }

    private String createLabelType = "create table labeltype(typename text primary key)";
    private String createLabelcontent = "create table labelcontent(labelname text primary key,typename text)";
    private String createSelected = "create table selectedlabel(labelname text)";
    private String createUser = "create table userinfo(" +
            "userID integer," +
            "userName text," +
            "userNicheng text," +
            "userSno text," +
            "userPhone text," +
            "userSex text," +
            "userSchool text," +
            "userPlace text," +
            "userIdentity text," +
            "userAutograph text," +
            "userlabel text," +
            "dynamicNumber text," +
            "followNumber text," +
            "followedNumber text," +
            "userSpeci text," +
            "headPic text," +
            "model text)";

    private String createLCUser = "create table lcuser(userID text,userChatID text,userName text,headPic text)";
    private final String  createthings = "create table things(thingsID text,userID text,userNicheng text,event text,thingsContent text,thingsDate text,thingsimage text,headPic text,commentNum text,likeNum text)";
    private final String createlosetake = "create table losetake(thingsID text,userID text,userNicheng text,event text,thingsContent text,thingsDate text,thingsimage text,headPic text)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createLabelcontent);
        db.execSQL(createLabelType);
        db.execSQL(createSelected);
        db.execSQL(createUser);
        db.execSQL(createLCUser);
        db.execSQL(createthings);
        db.execSQL(createlosetake);
        Log.d(TAG, "onCreate: 创建数据库表成功！");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: 更新表成功！");
    }
}
