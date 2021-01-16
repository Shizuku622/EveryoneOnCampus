package com.android.everyoneoncampus.model;

import com.android.everyoneoncampus.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserModel implements UserModelInterface {
    public static List<User> mUserList = new ArrayList<>();

    @Override
    public boolean userLogin(String userSno,String userPassword) {
        for(User user : mUserList){
            if(user.userSno.equals(userSno) && user.userPassword.equals(userPassword)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void userRegister(String u, String p) {
        User user = new User();
        user.userSno = u;
        user.userPassword = p;
        mUserList.add(user);
    }
}
