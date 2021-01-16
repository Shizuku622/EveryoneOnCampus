package com.android.everyoneoncampus.model;

public class UserModel implements UserModelInterface {
//    public static List<User> mUserList = new ArrayList<>();
//    private MySQLModel mMySqlModel = new MySQLModel();

    @Override
    public boolean userLogin(String userSno,String userPassword) {
//        for(User user : mUserList){
//            if(user.userSno.equals(userSno) && user.userPassword.equals(userPassword)){
//                return true;
//            }
//        }
//        return false;
//        if(mMySqlModel.userLogin(userSno,userPassword)){
//            return true;
//        }else{
//            return false;
//        }
        return false;
    }

    @Override
    public boolean userRegister(String u, String p) {
//        User user = new User();
//        user.userSno = u;
//        user.userPassword = p;
//        mUserList.add(user);
//        return mMySqlModel.registerUser(u,p);
        return false;

    }
}
