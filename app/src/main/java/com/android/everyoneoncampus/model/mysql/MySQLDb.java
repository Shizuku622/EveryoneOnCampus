package com.android.everyoneoncampus.model.mysql;

import android.util.Log;

import com.android.everyoneoncampus.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDb {
    private static final String TAG = "MySQLDb";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWD = "root";
    private final String IP = "106.52.183.106";
    private final String DBNAME = "rrxy";
    private final String URL = "jdbc:mysql://"+IP+":3306/"+DBNAME;

    private Connection getConnector(){
        Connection conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER,PASSWD);
        }catch (Exception e){
            Log.e(TAG, e.getMessage() );
        }
        return conn;
    }

    





}
