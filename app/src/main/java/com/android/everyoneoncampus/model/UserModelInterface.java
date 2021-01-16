package com.android.everyoneoncampus.model;

import com.android.everyoneoncampus.model.entity.User;

public interface UserModelInterface {
    boolean userLogin(String u, String p);
    void userRegister(String u,String p);

}
