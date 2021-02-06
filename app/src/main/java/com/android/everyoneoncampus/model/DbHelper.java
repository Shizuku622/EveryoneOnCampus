package com.android.everyoneoncampus.model;

import android.app.job.JobInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Pair;

import com.android.everyoneoncampus.EocApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private EOCDatabaseHelper mDbHelper;

    public  DbHelper(){
        mDbHelper = new EOCDatabaseHelper(EocApplication.getContext(),"EocDB",4);
        mDbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getSQLiteDatabase(){
        return mDbHelper.getWritableDatabase();
    }

    //保存用户数据
    public void saveUserInfo(User user){

    }

    //添加数据
    public void insertData(String sql){
        SQLiteDatabase db = getSQLiteDatabase();
        db.execSQL(sql);
    }

    //查询是否存在
    private boolean selectDataExist(String sql){
        SQLiteDatabase db = getSQLiteDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
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
        List<String> labelList = new ArrayList<>();
        labelList.clear();
        if(label.moveToFirst()){
            do{
                String ln = label.getString(label.getColumnIndex("labelname"));
                labelList.add(ln);
            }while(label.moveToNext());
        }
        return labelList;
    }
    //删除一个标签
    public void deleteSelectedLabel(String labelName){
        deleteData("delete from selectedlabel" + " where labelname='"+labelName+"'");
    }
    //选择一个标签
    public void SelectLable(String labelName){
        if(!selectDataExist("select * from selectedlabel where labelname='"+labelName+"'")){
            insertData("insert into selectedlabel values('"+labelName+"')");
        }
    }

    //计数
    public int getLabelCount(){
        Cursor cursorCount = selectData("select * from selectedlabel");
        int count = cursorCount.getCount();
        return count;
    }
    //清除数据
    public void clearSelectedLabel(){
        deleteData("delete from selectedlabel");
    }


    public User readUserInfo() {
        Cursor cursorUserInfo = selectData("select * from userinfo");
        User user = new User();
        if(cursorUserInfo.moveToFirst()){
            user.userPassword = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userPassword"));
            user.userName = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userName"));
            user.userSno = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userSno"));
            user.userPhone = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userPhone"));
            user.userSex = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userSex"));
            user.userSchool = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userSchool"));
            user.userPlace = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userPlace"));
            user.userIdentity = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userIdentity"));
            user.userIcon = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userIcon"));
            user.userAutograph = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("userAutograph"));
            user.mark = cursorUserInfo.getString(cursorUserInfo.getColumnIndex("mark"));
        }
        return user;
    }
}
