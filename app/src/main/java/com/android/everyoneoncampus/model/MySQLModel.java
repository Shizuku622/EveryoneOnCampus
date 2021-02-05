package com.android.everyoneoncampus.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.allinterface.ReturnSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLModel {
    //sharedpre
    private SPModel mSPModel = new SPModel();

    private static final String TAG = "MySQLModel";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "rrxymysql";
    private final String PASSWD = "RRXY@mysql0204";
    private final String IP = "49.235.216.56";
    private final String DBNAME = "rrxy";
    //&characterEncoding=utf8&useUnicode=true
    private final String OTHER = "?useSSL=false&serverTimezone=UTC";
    private final String URL = "jdbc:mysql://"+IP+":2021/"+DBNAME;

    public Connection getConnector(){
        Connection conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER,PASSWD);
        }catch (Exception e){
            Log.e(TAG, e.getMessage() );
        }
        return conn;
    }
    //登录
    public void userLogin(String user, String passwd, ReturnSQL returnSQL){
        new Thread(()->{
            Looper.prepare();
            String SQL = "select * from user where userSno = ? and userPassword = ?";
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setString(1,user);
                ps.setString(2,passwd);
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()){
                    User userInfo = new User();
                    userInfo.userID = resultSet.getInt("userID");
                    userInfo.userPassword = resultSet.getString("userPassword");
                    userInfo.userName = resultSet.getString("userName");
                    userInfo.userSno = resultSet.getString("userSno");
                    userInfo.userPhone = resultSet.getString("userPhone");
                    userInfo.userSex = resultSet.getString("userSex");
                    userInfo.userSchool = resultSet.getString("userSchool");
                    userInfo.userPlace = resultSet.getString("userPlace");
                    userInfo.userIdentity = resultSet.getString("userIdentity");
                    userInfo.userIcon = resultSet.getString("userIcon");
                    userInfo.userAutograph = resultSet.getString("userAutograph");
                    userInfo.userLabel = resultSet.getString("userLabel");
                    userInfo.mark = resultSet.getInt("mark");
                    //保存
                    mSPModel.saveUserInfo(userInfo);
                    returnSQL.onStatus(1);
                }else{
                    returnSQL.onStatus(0);
                }
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
                returnSQL.onStatus(0);
            }
            Looper.loop();
        }).start();
    }

    //注册
    public void registerUser(String user,String passwd,ReturnSQL returnSQL){
        new Thread(()->{
            Looper.prepare();
            String sql = "insert into user(userSno,userPassword) values(?,?)";
            try(Connection conn = getConnector();
                PreparedStatement ps = conn.prepareStatement(sql);)
            {
                ps.setString(1,user);
                ps.setString(2,passwd);
                int i = ps.executeUpdate();
                if(i != 0){
                    returnSQL.onStatus(1);
                }else{
                    returnSQL.onStatus(0);
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
                Log.e(TAG, throwables.getMessage());
                returnSQL.onStatus(0);
            }
            Looper.loop();
        }).start();
    }
    //保存数据
    public void  getLabelTitle(DataListener<Pair<List<String>,LabelAll>> dataListener){

        new Thread(()->{
            Pair<List<String>,LabelAll> label = new Pair<>(new ArrayList<>(),new LabelAll());
            String sql1 = "SELECT * FROM labeltype";
            String sql2 = "SELECT * FROM labelcontent";
            try(Connection conn = getConnector();PreparedStatement ps1 = conn.prepareStatement(sql1);PreparedStatement ps2 = conn.prepareStatement(sql2)){
                ResultSet resultSet1 = ps1.executeQuery();
                ResultSet resultSet2 = ps2.executeQuery();
                while (resultSet1.next()){
                    String title = resultSet1.getString("typename");
                    label.first.add(title);
                }
                while (resultSet2.next()){
                    String typename = resultSet2.getString("typename");
                    String labelname = resultSet2.getString("labelname");
                    label.second.addLabel(typename.trim(),labelname.trim());
                }
                dataListener.onComplete(label);
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();




    }


    //获取标签和内容
//    public void  getLabelTitle(DataListener<Pair<List<String>,LabelAll>> dataListener){
////        final List<Pair<List<String>,LabelAll>> pair = new ArrayList<>();
////        Handler handler = new Handler(Looper.myLooper()){
////            @Override
////            public void handleMessage(@NonNull Message msg) {
////                super.handleMessage(msg);
////                switch (msg.what){
////                    case 1:
////                        Pair<List<String>,LabelAll> temp = (Pair<List<String>,LabelAll>)msg.obj;
////                        pair.add(temp);
////                        break;
////                }
////            }
////        };
//
//        new Thread(()->{
//            Looper.prepare();
//            Pair<List<String>,LabelAll> label = new Pair<>(new ArrayList<>(),new LabelAll());
//            String sql1 = "SELECT * FROM labeltype";
//            String sql2 = "SELECT * FROM labelcontent";
//            try(Connection conn = getConnector();PreparedStatement ps1 = conn.prepareStatement(sql1);PreparedStatement ps2 = conn.prepareStatement(sql2)){
//                ResultSet resultSet1 = ps1.executeQuery();
//                ResultSet resultSet2 = ps2.executeQuery();
//                while (resultSet1.next()){
//                    String title = resultSet1.getString("typename");
//                    label.first.add(title);
//                }
//                while (resultSet2.next()){
//                    String typename = resultSet2.getString("typename");
//                    String labelname = resultSet2.getString("labelname");
//                    label.second.addLabel(typename.trim(),labelname.trim());
//                }
////                Message message = Message.obtain();
////                message.what = 1;
////                message.obj = label;
////                handler.sendMessage(message);
//                dataListener.onComplete(label);
//            }catch (Exception e){
//                Log.d(TAG, e.getMessage());
//            }
//            Looper.loop();
//        }).start();
//
//
//
//
//    }



    //注册 学号和密码
//    public boolean registerUser(String user,String passwd){
//        final List<Boolean> flag = new ArrayList<>();
//        flag.add(false);
//        new Thread(()->{
//            String sql = "insert into user(userSno,userPassword) values(?,?)";
//            try(Connection conn = getConnector();
//                PreparedStatement ps = conn.prepareStatement(sql);)
//            {
//                ps.setString(1,user);
//                ps.setString(2,passwd);
//                int i = ps.executeUpdate();
//                if(i != 0){
//                    flag.set(0,true);
//                }else
//                    flag.set(0,false);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                Log.e(TAG, throwables.getMessage());
//                flag.set(0,false);
//            }
//        }).start();
//        return flag.get(0);
//    }

    //登录
//    public boolean userLogin(String user,String passwd){
//        final List<Boolean> flag = new ArrayList<>();
//        flag.add(false);
//        Handler handler = new Handler(Looper.myLooper()){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                switch (msg.what){
//                    case 1:
//                        if(msg.arg1 == 0){
//                            flag.set(0,false);
//                        }
//                        else{
//                            flag.set(0,true);
//                        }
//                        break;
//                }
//            }
//        };
//        new Thread(()->{
//            Message msg = Message.obtain();
//            msg.what = 1;
//            String sql = "select * from user";
//            try(Connection conn = getConnector(); PreparedStatement ps = conn.prepareStatement(sql)){
//                ResultSet rs =  ps.executeQuery();
//                if(rs.getRow() > 0){
//                    msg.arg1 = 1;
//                }else
//                    msg.arg1 = 0;
//            }catch (Exception e){
//                Log.e(TAG, e.getMessage() );
//                msg.arg1 = 0;
//            }
//            handler.sendMessage(msg);
//        }).start();
//        return  flag.get(0);
//    }

}
