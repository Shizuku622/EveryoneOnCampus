package com.android.everyoneoncampus.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.everyoneoncampus.EocApplication;

public class EOCDatabaseHelper extends SQLiteOpenHelper {


    public EOCDatabaseHelper(@Nullable Context context, @Nullable String name,  int version) {
        super(context, name, null, version);
    }

    private String createLabelType = "create table labeltype(typename text primary key)";
    private String createLabelcontent = "create table labelcontent(labelname text primary key,typename text)";
    private String createSelected = "create table selectedlabel(labelname text)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createLabelcontent);
        db.execSQL(createLabelType);
        Toast.makeText(EocApplication.getContext(),"创建成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(createSelected);
        db.execSQL("insert into selectedlabel values('轻音乐')");
        db.execSQL("insert into selectedlabel values('乡村')");
        db.execSQL("insert into selectedlabel values('古典')");
        db.execSQL("insert into selectedlabel values('摇滚')");
        db.execSQL("insert into selectedlabel values('流行')");
        Toast.makeText(EocApplication.getContext(),"更新成功!",Toast.LENGTH_LONG).show();
    }
}
