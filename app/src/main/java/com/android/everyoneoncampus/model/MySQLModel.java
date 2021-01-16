package com.android.everyoneoncampus.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.ReturnSQL;
import com.android.everyoneoncampus.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

public class MySQLModel {
    private static final String TAG = "MySQLModel";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWD = "root";
    private final String IP = "106.52.183.106";
    private final String DBNAME = "rrxy";
    private final String URL = "jdbc:mysql://"+IP+":3306/"+DBNAME;

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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Log.e(TAG, throwables.getMessage());
                returnSQL.onStatus(0);
            }
            Looper.loop();
        }).start();
    }


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
