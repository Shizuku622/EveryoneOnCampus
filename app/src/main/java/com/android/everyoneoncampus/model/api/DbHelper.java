package com.android.everyoneoncampus.model.api;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.LabelAll;
import com.android.everyoneoncampus.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DbHelper {
    private EOCDatabaseHelper mDbHelper;
    private SPModel mSPModel = new SPModel();

    private static final String TAG = "DbHelper";

    public  DbHelper(){
        mDbHelper = new EOCDatabaseHelper(EocApplication.getContext(),"EocDB",5);
        mDbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getSQLiteDatabase(){
        return mDbHelper.getWritableDatabase();
    }
    /*
    * 最新的方法
    *
    * */

    public void selectAllLCUserInfo(DataListener<List<User>> dataListener){
        String sql = String.format("select * from lcuser");
        Cursor cursor = selectData(sql);
        List<User> userList = new ArrayList<>();
        while(cursor.moveToNext()){
            User temp = new User();
            temp.userID = cursor.getString(cursor.getColumnIndex("userID"));
            temp.userName = cursor.getString(cursor.getColumnIndex("userName"));
            byte[] heapic = Base64.decode(cursor.getString(cursor.getColumnIndex("headPic")),Base64.DEFAULT);
            temp.headPic = heapic;
            userList.add(temp);
        }
        if(userList.size() == 0){
            dataListener.onComplete(null);
        }else{
            dataListener.onComplete(userList);
        }

    }


    /**
     * @param userID 查询用户ID
     * @return true表示有该用户，false表示无该用户
     */
    public boolean selectLCUserInfo(String userID){
        String sql = String.format("select * from lcuser where userID = '%s'",userID);
        Cursor cursor = selectData(sql);
        if(cursor.moveToNext()){
            return true;
        }
        return false;
    }

    /**
     * @param user 用户信息
     * 更新已有的信息，通过ID查询
     */
    public void updateLCUserInfo(User user){
        String sql = String.format("update lcuser set userName='%s',headPic='%s' where userChatID='%s' and userID='%s'",user.userName,user.headPic,user.userID,mSPModel.readUserID());
        updateDate(sql);
    }

    /**
     * 保存lc信息
     */
    public void insertLCUserInfo(User user){
        String sql = String.format("INSERT INTO lcuser(userID,userChatID,userName,headPic) VALUES('%s','%s','%s','%s')",mSPModel.readUserID(),user.userID, user.userName,user.headPic);
        insertData(sql);
    }


    //更新用户信息
    public void updateExistUserInfo(User user){
        try{
            String sql = String.format("update userinfo set " +
                            "userName='%s'," +
                            "userNicheng='%s'," +
                            "userSno='%s'," +
                            "userPhone='%s'," +
                            "userSex='%s'," +
                            "userSchool='%s'," +
                            "userPlace='%s'," +
                            "userIdentity='%s'," +
                            "userAutograph='%s'," +
                            "userlabel ='%s'," +
                            "dynamicNumber='%s'," +
                            "followNumber='%s'," +
                            "followedNumber='%s'," +
                            "userSpeci='%s'," +
                            "headPic='%s' where userID='%s'" +
                            "",user.userName,user.userNicheng,user.userSno,user.userPhone,user.userSex, user.userSchool,user.userPlace,user.userIdentity,
                    user.userAutograph,user.userlabel,user.dynamicNumber,user.followNumber,user.followedNumber,user.userSpeci,EocTools.byteConvertString(user.headPic),
                    user.userID);
            updateDate(sql);
            Log.d(TAG, "updateExistUserInfo: 更新成功！");
        }catch(Exception e){
            Log.d(TAG, "updateExistUserInfo: "+e.getMessage());
        }


    }


    //查询用户是否已经存在在SQLite数据库表里
    public boolean selectUserExist(String userID){
        String sql = String.format("select * from userinfo where userID = '%s'",userID);
        Cursor cursor = selectData(sql);
        if(cursor.moveToNext()){
            //查询到返回 真
            return true;
        }
        //false表示查询不到
        return false;
    }

    //读取当前用户信息
    public void readCurrentUserInfo(DataListener<User> dataListener){
        SQLiteDatabase db = getSQLiteDatabase();
        String sql = String.format("SELECT * FROM userinfo where userID = '%s'",mSPModel.readUserID());
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            byte[] headPic = EocTools.stringConvertByte(cursor.getString(cursor.getColumnIndex("headPic")));
            User user = new User();
            user.userID = cursor.getString(cursor.getColumnIndex("userID"));
            user.userName = cursor.getString(cursor.getColumnIndex("userName"));
            user.userNicheng = cursor.getString(cursor.getColumnIndex("userNicheng"));
            user.userSno = cursor.getString(cursor.getColumnIndex("userSno"));
            user.userPhone = cursor.getString(cursor.getColumnIndex("userPhone"));
            user.userSex = cursor.getString(cursor.getColumnIndex("userSex"));
            user.userSchool = cursor.getString(cursor.getColumnIndex("userSchool"));
            user.userPlace = cursor.getString(cursor.getColumnIndex("userPlace"));
            user.userIdentity = cursor.getString(cursor.getColumnIndex("userIdentity"));
            user.userAutograph = cursor.getString(cursor.getColumnIndex("userAutograph"));
            user.userlabel = cursor.getString(cursor.getColumnIndex("userlabel"));
            user.dynamicNumber = cursor.getString(cursor.getColumnIndex("dynamicNumber"));
            user.followNumber = cursor.getString(cursor.getColumnIndex("followNumber"));
            user.followedNumber = cursor.getString(cursor.getColumnIndex("followedNumber"));
            user.userSpeci = cursor.getString(cursor.getColumnIndex("userSpeci"));
            user.headPic = headPic;
            user.model = cursor.getString(cursor.getColumnIndex("model"));
            dataListener.onComplete(user);
        }else
        {
            dataListener.onComplete(null);
        }
    }



    //添加数据
    public void saveCurrentUserInfo(User user){
//        deleteData("delete from labeltype");

        String headPid = EocTools.byteConvertString(user.headPic);
        String sql = String.format("INSERT INTO userinfo(" +
                        "userID," +
                        "userName," +
                        "userNicheng," +
                        "userSno," +
                        "userPhone," +
                        "userSex," +
                        "userSchool," +
                        "userPlace," +
                        "userIdentity," +
                        "userAutograph," +
                        "userlabel," +
                        "dynamicNumber," +
                        "followNumber," +
                        "followedNumber," +
                        "userSpeci," +
                        "headPic," +
                        "model)  VALUES(%s,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                user.userID,user.userName,user.userNicheng,user.userSno,user.userPhone,user.userSex,user.userSchool,user.userPlace,user.userIdentity,
                user.userAutograph,user.userlabel,user.dynamicNumber,user.followNumber,user.followedNumber,user.userSpeci,headPid,user.model);
        insertData(sql);
        Log.d(TAG, "saveCurrentUserInfo: 插入成功！");
    }

    //更新数据
    public void updateDate(String sql){
        SQLiteDatabase db = getSQLiteDatabase();
        db.execSQL(sql);
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
    public void insertLabelData(List<String> label, LabelAll labelAll){
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

    //计数 标签
    public int getLabelCount(){
        Cursor cursorCount = selectData("select * from selectedlabel");
        int count = cursorCount.getCount();
        return count;
    }
    //清除数据
    public void clearSelectedLabel(){
        deleteData("delete from selectedlabel");
    }

}
