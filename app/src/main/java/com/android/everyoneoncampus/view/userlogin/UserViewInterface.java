package com.android.everyoneoncampus.view.userlogin;

import com.android.everyoneoncampus.model.entity.User;

public interface UserViewInterface {
    void userWriteUserInfo();
    void userMainUserUI();
    void showProgressLogin();
    void hideProgressLogin();
    void loginMainUI(User user);
}
