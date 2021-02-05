package com.android.everyoneoncampus.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Pair;

import com.android.everyoneoncampus.EocApplication;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private EOCDatabaseHelper mDbHelper;

    public  DbHelper(){
        mDbHelper = new EOCDatabaseHelper(EocApplication.getContext(),"EocDB",2);
        mDbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getSQLiteDatabase(){
        return mDbHelper.getWritableDatabase();
    }

    //添加数据
    private void insertData(String sql){
        SQLiteDatabase db = getSQLiteDatabase();
        db.execSQL(sql);
    }

    //查询数据
    private Cursor selectData(String sql){
        SQLiteDatabase db = getSQLiteDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }
    //删除数据
    private void deleteData(String sql){
        SQLiteDatabase db = getSQLiteDatabase();
        db.execSQL(sql);
    }

    //添加标签数据
    public void insertLabelData(List<String> label,LabelAll labelAll){
        deleteData("delete from labeltype");
        deleteData("delete from labelcontent");
        for(int i = 0;i < label.size();i++){
            //插入标签
            insertData("INSERT INTO labeltype VALUES('"+label.get(i)+"')");
            //插入标签的内容
            List<String> content = labelAll.getLabelName(label.get(i));
            for(int j = 0;j < content.size();j++){
                String sql = "INSERT INTO labelcontent(labelname,typename) VALUES('"+ content.get(j) +"','"+label.get(i)+"')";
                insertData(sql);
            }
        }
    }

    //查询标签数据
    public Pair<List<String> ,LabelAll > readLabelData(){
        Cursor typename = selectData("SELECT * FROM labeltype");
        Cursor labelname = selectData("SELECT * FROM labelcontent");
        Pair<List<String>,LabelAll> label = new Pair<>(new ArrayList<>(),new LabelAll());
        if(typename.moveToFirst()){
            do{
                String type = typename.getString(typename.getColumnIndex("typename"));
                label.first.add(type);
            }while(typename.moveToNext());
        }
        if(labelname.moveToFirst()){
            do{
                String type = labelname.getString(labelname.getColumnIndex("typename"));
                String name = labelname.getString(labelname.getColumnIndex("labelname"));
                label.second.addLabel(type,name);
            }while(labelname.moveToNext());
        }
        return label;
    }

    //已选的标签
    public List<String> getSelectedLabelData(){
        Cursor label = selectData("select * from selectedlabel");
        if(label.moveToFirst()){
            do{
                String ln = label.getString(label.getColumnIndex("labelname"));
            }while(label.moveToNext());
        }
    }



}
