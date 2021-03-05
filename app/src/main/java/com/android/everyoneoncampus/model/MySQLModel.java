package com.android.everyoneoncampus.model;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.allinterface.ReturnSQL;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MySQLModel {
    //sharedpre
    private SPModel mSPModel = new SPModel();
    private DbHelper mDbHelper = new DbHelper();

    private static final String TAG = "MySQLModel";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "rrxymysql";
    private final String PASSWD = "RRXY@mysql0204";
    private final String IP = "49.235.216.56";
    private final String DBNAME = "rrxy";
    //&characterEncoding=utf8&useUnicode=true
    private final String OTHER = "?useSSL=false&serverTimezone=UTC";
    private final String URL = "jdbc:mysql://"+IP+":2021/"+DBNAME;
    //获取connector
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

    /*
    * 最新的方法
    *
    * */

    //获取动态、关注、被关注数量
    public void getDynamicFollow(DataListener<String> dataListener){

        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((String)msg.obj);
                        break;
                }
            }
        };

        new Thread(()->{
            String SQL1 = String.format("select * from things where userID='%s'",mSPModel.readUserInfo());
            String SQL2 = String.format("select * from problem where userID='%s'",mSPModel.readUserInfo());
            String SQL3 = String.format("select * from lose where userID='%s'",mSPModel.readUserInfo());
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL1)) {
                int dynamicNumber = 0;
                int followNumber = 0;
                int followedNumber = 0;
                ResultSet resultSet = ps.executeQuery();
                dynamicNumber += resultSet.getRow();
                ps.close();




                Message message = Message.obtain();

                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }).start();
    }

    //获取用户信息
    public void getUserLogin(String user,String passwd,DataListener dataListener){

        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((User)msg.obj);
                        break;
                    case 2:
                        dataListener.onComplete(null);
                        break;
                }
            }
        };

        new Thread(()->{
            String SQL = "select * from user where userSno = ? and userPassword = ?";
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setString(1,user);
                ps.setString(2,passwd);
                ResultSet resultSet = ps.executeQuery();
                Message message = Message.obtain();
                User userInfo;
                if(resultSet.next()){
                    userInfo = User.getUser(
                            resultSet.getString("userPassword"),
                            resultSet.getString("userName"),
                            resultSet.getString("userSno"),
                            resultSet.getString("userPhone"),
                            resultSet.getString("userSex"),
                            resultSet.getString("userSchool"),
                            resultSet.getString("userPlace"),
                            resultSet.getString("userIdentity"),
                            resultSet.getString("userIcon"),
                            resultSet.getString("userAutograph"),
                            resultSet.getString("userlabel"),
                            resultSet.getString("mark"),
                            resultSet.getString("userID"),
                            resultSet.getString("userNicheng"),
                            resultSet.getString("dynamicNumber"),
                            resultSet.getString("followNumber"),
                            resultSet.getString("followedNumber"));
                    message.what = 1;
                    message.obj = userInfo;
                }else{
                    message.what = 2;
                }
                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }).start();
    }

    //获取当前用户信息
    public void getCurrentUserInfo(DataListener dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((String)msg.obj);
                        break;
                }
            }
        };
        String userSno = mSPModel.readUserInfo();
        new Thread(()->{
            String SQL = String.format("select * from user where userSno='%s'",userSno);
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL)) {
                ResultSet resultSet = ps.executeQuery();
                while(resultSet.next()){
                    String sno = resultSet.getString("userSno");
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = sno;
                    handler.sendMessage(msg);
                }
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
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
    //更新用户信息
    public void updateUserInfo(String user,String sex,String ident,String label,String nicheng,String xingming,String qianming){
        String sql = String.format("update user set userSex='%s',userIdentity='%s',userlabel='%s',mark='%s',userName='%s',userAutograph='%s',userNicheng='%s'" +
                "where userSno = '%s'",sex,ident,label,"1",xingming,qianming,nicheng,user);
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps1 = conn.prepareStatement(sql)){
                int i = ps1.executeUpdate();
                Log.d(TAG, i+"条影响");
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();
    }


    public void getThingsAllApi(DataListener<List<Things>> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((List<Things>)msg.obj);
                        break;
                    case 2:
                        dataListener.onComplete(null);
                }
            }
        };

        String sql = "select * from things";
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet resultSet = ps.executeQuery();
                List<Things> thingsList = new ArrayList<>();
                if(resultSet.first()){
                    do{
                        String userID = resultSet.getString("userID");
                        String thingsContent = resultSet.getString("thingsContent");
                        String thingsDate = resultSet.getString("thingsDate");
                        thingsList.add(new Things(userID,thingsDate,thingsContent));
                    }while(resultSet.next());
                }
                Message msg = Message.obtain();
                if(thingsList.isEmpty()){
                    msg.what = 2;
                }else{
                    msg.what = 1;
                    msg.obj = thingsList;
                }
                handler.sendMessage(msg);
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();
    }
    //发送
    public void sendNewSomethingApi(String sql){

        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Toast.makeText(EocApplication.getContext(),"发送成功!",Toast.LENGTH_SHORT).show();
            }
        };

        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(sql)){
                int resultSet = ps.executeUpdate();
                handler.sendMessage(Message.obtain());
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();
    }

}
