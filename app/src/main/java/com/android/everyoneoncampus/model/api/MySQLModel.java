package com.android.everyoneoncampus.model.api;

import android.os.Build;
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
import com.android.everyoneoncampus.model.LabelAll;
import com.android.everyoneoncampus.model.entity.Comment;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.model.entity.User;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MySQLModel {
    //sharedpre
    private SPModel mSPModel = new SPModel();
    private DbHelper mDbHelper = new DbHelper();

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

    private static final String TAG = "MySQLModel";
    /*
    * 最上面是最新的方法
    *
    * */




    /**
     * 获得关注人的动态
     */
    public void getGuanzhuDynamicApi(DataListener<List<Things>> dataListener){
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

        String sql = String.format("SELECT * FROM `things` " +
                "inner JOIN `user` " +
                "inner JOIN follow " +
                "on  follow.userID = %s and follow.userIDed = things.userID and `user`.userID = follow.userIDed",mSPModel.readUserID());
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet resultSet = ps.executeQuery();
                List<Things> thingsList = new ArrayList<>();
                if(resultSet.first()){
                    do{
                        String thingsID = resultSet.getString("thingsID");
                        String userID = resultSet.getString("userID");
                        String userNicheng = resultSet.getString("userNicheng");
                        String event = resultSet.getString("event");
                        String thingsContent = resultSet.getString("thingsContent");
                        String thingsDate = resultSet.getString("thingsDate");
                        byte[] thingsImage = resultSet.getBytes("thingsImage");
                        byte[] headPic = resultSet.getBytes("headPic");
                        thingsList.add(new Things(thingsID,userID,userNicheng,event, thingsContent,thingsDate,thingsImage,headPic));
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

    //添加评论
    public void insertThingsCommentApi(Comment comment, DataListener<Integer> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                dataListener.onComplete(msg.what);
            }
        };
        new Thread(()->{
            String sql = "INSERT INTO `Comment`(thingsID,userID,CContent) VALUES(?,?,?)";
            Message msg = Message.obtain();
            try(Connection conn = getConnector();
                PreparedStatement ps = conn.prepareStatement(sql);)
            {
                ps.setString(1,comment.thingsID);
                ps.setString(2,comment.userID);
                ps.setString(3,comment.CContent);
                int i = ps.executeUpdate();
                if(i != 0){
                    msg.what = 1;
                }else{
                    msg.what = 2;
                }
                handler.sendMessage(msg);
            } catch (Exception throwables) {
                throwables.printStackTrace();
                Log.e(TAG, throwables.getMessage());
                msg.what = 3;
                handler.sendMessage(msg);
            }
        }).start();
    }

    //获得事件的评论
    public void getThingsCommentApi(String thingsID, DataListener<List<Comment>> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                dataListener.onComplete((List<Comment>)msg.obj);
            }
        };
        String sql = String.format("SELECT * FROM `Comment` " +
                "join `user` " +
                "on `Comment`.userID = `user`.userID and thingsID = %s",thingsID);
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps1 = conn.prepareStatement(sql)){
                ResultSet resultSet = ps1.executeQuery();
                List<Comment> comments = new ArrayList<>();
                while(resultSet.next()){
                    Comment comment = new Comment(resultSet.getString("CommentID"),
                            resultSet.getString("thingsID"),
                            resultSet.getString("userID"),
                            resultSet.getString("userNicheng"),
                            resultSet.getString("CContent"),
                            resultSet.getString("CDate"),
                            resultSet.getBytes("headPic"));
                    comments.add(comment);
                }

                Message msg = Message.obtain();
                msg.obj = comments;
                handler.sendMessage(msg);
                Log.d(TAG, "getThingsComment: 获取评论成功");
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();
    }



    //查询登录的机型和本机型是否一样
    public void getModelInfoApi(DataListener<String> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                dataListener.onComplete((String)msg.obj);
            }
        };
        String sql = String.format("SELECT model FROM `user` where userID= %s",mSPModel.readUserID());
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps1 = conn.prepareStatement(sql)){
                ResultSet resultSet = ps1.executeQuery();
                String model = "";
                if(resultSet.next()){
                    model = resultSet.getString("model");
                }
                Message msg = Message.obtain();
                msg.obj = model;
                handler.sendMessage(msg);
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();
    }


    //记录登录过得最新的机型
    public void writeModelInfoApi(){
        //记录在云端
        String sql = String.format("update `user` set model = '%s' where userID = %s", Build.MODEL,mSPModel.readUserID());
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps1 = conn.prepareStatement(sql)){
                int i = ps1.executeUpdate();
                Log.d(TAG, i+"条影响");
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();
    }

    //获得个人动态
    public void getUserDynamic(DataListener<List<Things>> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((List<Things>)msg.obj);
                        Log.d(TAG, "传递List<Things>");
                        break;
                }
            }
        };
        new Thread(()->{
            String sql = String.format("SELECT * FROM `things` join `user` on  things.userID = '%s' and `user`.userID = things.userID",mSPModel.readUserID());
            //我关注的sql
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet resultSet = ps.executeQuery();
                //列表
                Message message = Message.obtain();
                List<Things> things = new ArrayList<>();
                while (resultSet.next()){
                    Things temp = new Things(
                            resultSet.getString("thingsID"),
                            resultSet.getString("userID"),
                            resultSet.getString("userNicheng"),
                            resultSet.getString("event"),
                            resultSet.getString("thingsContent"),
                            resultSet.getString("thingsDate"),
                            resultSet.getBytes("thingsimage"),
                            resultSet.getBytes("headPic"));
                    things.add(temp);
                }
                message.what = 1;
                message.obj = things;
                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }

        }).start();
    }


    //获取关注和被关注的列表
    public void getTwoFollowList(int followChoose,DataListener<List<User>> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                dataListener.onComplete((List<User>)msg.obj);
                Log.d(TAG, "传递List<User>");
            }
        };
        new Thread(()->{
            String sql = "";
            //我关注的sql
            if(followChoose == 1){
                sql = String.format("select * " +
                        "from follow " +
                        "join  `user` " +
                        "join  school " +
                        "on `user`.userID = follow.userIDed and school.schoolID = `user`.userSchool and follow.userID = %s", mSPModel.readUserID());
            }else{//被关注的
                sql = String.format("select * " +
                        "from follow " +
                        "join  `user` " +
                        "join  school " +
                        "on `user`.userID = follow.userID  and school.schoolID = `user`.userSchool and follow.userIDed = %s", mSPModel.readUserID());
            }
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet resultSet = ps.executeQuery();
                //列表
                List<User> followUserList = new ArrayList<>();
                while(resultSet.next()){
                    User temp = new User();
                    if(followChoose == 1){
                        temp.userID = resultSet.getString("userIDed");
                    }else{
                        temp.userID = resultSet.getString("userID");
                    }
                    temp.headPic = resultSet.getBytes("headPic");
                    temp.userNicheng = resultSet.getString("userNicheng");
                    temp.userAutograph = resultSet.getString("userAutograph");
                    temp.userName = resultSet.getString("userName");
                    temp.userSex = resultSet.getString("userSex");
                    temp.userSchool = resultSet.getString("schoolName");
                    temp.userSno = resultSet.getString("userSno");
                    temp.userPhone = resultSet.getString("userPhone");
                    temp.userPlace = resultSet.getString("userPlace");
                    temp.userSpeci = resultSet.getString("userSpeci");
                    temp.userIdentity = resultSet.getString("userIdentity");
                    followUserList.add(temp);
                }
                Message message = Message.obtain();
                message.obj = followUserList;
                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }

        }).start();
    }


    public void testUploadPic(byte[] img){
        new Thread(()->{
            String sql = String.format("insert into test(pic) values(?)");
            try(Connection conn = getConnector(); PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setBytes(1,img);
                int i = ps.executeUpdate();
                Log.d(TAG, "插入图片影响："+i);
            } catch (Exception throwables) {
                throwables.printStackTrace();
                Log.e(TAG, throwables.getMessage());
            }
        }).start();
    }

    public void testGetPic(DataListener<byte[]> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((byte[]) msg.obj);
                        break;
                }
            }
        };

        new Thread(()->{
            String SQL1 = String.format("select pic from test");
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL1)) {
                ResultSet resultSet = ps.executeQuery();
                Message message = Message.obtain();
                message.what = 1;
                if(resultSet.next()){
                    message.obj = resultSet.getBytes("pic");
                }
                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }).start();
    }

    //获取头像
    public void getPic(DataListener<byte[]> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((byte[]) msg.obj);
                        break;
                }
            }
        };

        new Thread(()->{
            String SQL1 = String.format("select headpic from user where userId='%s'",EocApplication.getUserInfo().userID);
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL1)) {
                ResultSet resultSet = ps.executeQuery();
                Message message = Message.obtain();
                message.what = 1;
                if(resultSet.next()){
                    message.obj = resultSet.getBytes("headpic");
                }
                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }).start();
    }

    //上传头像
    public void uploadPic(byte[] img){
        new Thread(()->{
            String sql = String.format("update user set headpic=? where userID='%s'",EocApplication.getUserInfo().userID);
            try(Connection conn = getConnector(); PreparedStatement ps = conn.prepareStatement(sql);)
            {
                Blob blob = conn.createBlob();
                blob.setBytes(1,img);
                ps.setBlob(1,blob);
                int i = ps.executeUpdate();
                Log.d(TAG, "插入图片影响："+i);
            } catch (Exception throwables) {
                throwables.printStackTrace();
                Log.e(TAG, throwables.getMessage());
            }
        }).start();
    }

    //获得被关注
    public void getFollowed(DataListener<String> dataListener){
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
            String SQL1 = String.format("select count(userIDed) as userIDedfollow from follow where userIDed='%s'",mSPModel.readUserID());
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL1)) {
                ResultSet resultSet = ps.executeQuery();
                Message message = Message.obtain();
                message.what = 1;
                if(resultSet.next()){
                    message.obj = resultSet.getString("userIDedfollow");
                }

                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }).start();
    }

    //获得关注数量
    public void getFollow(DataListener<String> dataListener){
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
            String SQL1 = String.format("select count(userID) as userIDfollow from follow where userID='%s'",mSPModel.readUserID());
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL1)) {
                ResultSet resultSet = ps.executeQuery();
                Message message = Message.obtain();
                message.what = 1;
                if(resultSet.next()){
                    message.obj = resultSet.getString("userIDfollow");
                }
                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }).start();
    }


    //获取动态数量
    public void getDynamicNumber(DataListener<String> dataListener){

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
            String SQL1 = String.format("select count(userID) as userIDNumber from things where userID='%s'",mSPModel.readUserID());
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL1)) {
                ResultSet resultSet = ps.executeQuery();
                Message message = Message.obtain();
                message.what = 1;
                if(resultSet.next()){
                    message.obj = resultSet.getString("userIDNumber");
                }

                handler.sendMessage(message);
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }).start();
    }

    //登录
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
                    userInfo = new  User(
                            resultSet.getString("userID"),
                            resultSet.getString("userName"),
                            resultSet.getString("userSno"),
                            resultSet.getString("userPhone"),
                            resultSet.getString("userSex"),
                            resultSet.getString("userSchool"),
                            resultSet.getString("userPlace"),
                            resultSet.getString("userIdentity"),
                            resultSet.getString("userAutograph"),
                            resultSet.getString("userlabel"),
                            resultSet.getString("mark"),
                            resultSet.getString("userNicheng"),
                            resultSet.getString("dynamicNumber"),
                            resultSet.getString("followNumber"),
                            resultSet.getString("followedNumber"),
                            resultSet.getString("userSpeci"),
                            resultSet.getBytes("headPic"),
                            resultSet.getString("model"));
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
    public void getCurrentUserInfoApi(DataListener<User> dataListener){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        dataListener.onComplete((User)msg.obj);
                        break;
                }
            }
        };
        new Thread(()->{
            String SQL = String.format("select * from user where userID='%s'",mSPModel.readUserID());
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(SQL)) {
                ResultSet resultSet = ps.executeQuery();
                User userInfo = new User();
                while(resultSet.next()){
                    userInfo = new User(
                            resultSet.getString("userID"),
                            resultSet.getString("userName"),
                            resultSet.getString("userSno"),
                            resultSet.getString("userPhone"),
                            resultSet.getString("userSex"),
                            resultSet.getString("userSchool"),
                            resultSet.getString("userPlace"),
                            resultSet.getString("userIdentity"),
                            resultSet.getString("userAutograph"),
                            resultSet.getString("userlabel"),
                            resultSet.getString("mark"),
                            resultSet.getString("userNicheng"),
                            resultSet.getString("dynamicNumber"),
                            resultSet.getString("followNumber"),
                            resultSet.getString("followedNumber"),
                            resultSet.getString("userSpeci"),
                            resultSet.getBytes("headPic"),
                            resultSet.getString("model"));
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = userInfo;
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
    public void  getLabelTitle(DataListener<Pair<List<String>, LabelAll>> dataListener){

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
    public void updateUserInfo(String lable){
        String sql = String.format("update user set " +
                "userNicheng='%s' ," +
                "userName='%s'," +
                "userAutograph='%s'," +
                "userSpeci='%s'," +
                "userSex='%s'," +
                "userIdentity='%s'," +
                "userlabel='%s'," +
                "mark='%s' " +
                "where userID = '%s'",
                EocApplication.getUserInfo().userNicheng,
                EocApplication.getUserInfo().userName,
                EocApplication.getUserInfo().userAutograph,
                EocApplication.getUserInfo().userSpeci,
                EocApplication.getUserInfo().userSex,
                EocApplication.getUserInfo().userIdentity,
                lable,
                "1",
                EocApplication.getUserInfo().userID);
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps1 = conn.prepareStatement(sql)){
                int i = ps1.executeUpdate();
                Log.d(TAG, i+"条影响");
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }).start();
    }

    //获得所有的事件

    public void getThingsApi(DataListener<List<Things>> dataListener){
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

        String sql = "SELECT things.thingsID,things.userID,`user`.userNicheng,things.`event`,things.thingsContent,things.thingsDate,things.thingsImage,`user`.headPic  FROM `things` inner JOIN `user` on things.userID = `user`.userID ";
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet resultSet = ps.executeQuery();
                List<Things> thingsList = new ArrayList<>();
                if(resultSet.first()){
                    do{
                        String thingsID = resultSet.getString("thingsID");
                        String userID = resultSet.getString("userID");
                        String userNicheng = resultSet.getString("userNicheng");
                        String event = resultSet.getString("event");
                        String thingsContent = resultSet.getString("thingsContent");
                        String thingsDate = resultSet.getString("thingsDate");
                        byte[] thingsImage = resultSet.getBytes("thingsImage");
                        byte[] headPic = resultSet.getBytes("headPic");
                        thingsList.add(new Things(thingsID,userID,userNicheng,event, thingsContent,thingsDate,thingsImage,headPic));
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
    public void sendNewSomethingApi(String sql,byte[] thingsImage){
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1){
                    Toast.makeText(EocApplication.getContext(),"发送成功!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EocApplication.getContext(), "发送失败，请重试！", Toast.LENGTH_SHORT).show();
                }
            }
        };
        new Thread(()->{
            try(Connection conn = getConnector();PreparedStatement ps = conn.prepareStatement(sql)){
                Blob imageBlob = conn.createBlob();
                imageBlob.setBytes(1,thingsImage);
                ps.setBlob(1,imageBlob);

                int resultSet = ps.executeUpdate();
                Message msg = Message.obtain();
                if(resultSet != 0){
                    msg.what = 1;
                    Log.d(TAG, "插入事件影响" + resultSet + "条");
                }else{
                    msg.what = 2;
                    Log.d(TAG, "插入事件失败！");
                }
                handler.sendMessage(msg);
            }catch (Exception e){
                Log.d(TAG, e.getMessage());
                Log.d(TAG, "插入事件失败！");
            }
        }).start();
    }

}
