package com.android.everyoneoncampus.view.user;

import com.android.everyoneoncampus.model.entity.User;

public interface UserViewInterface {
    void userWriteUserInfo();
    void userMainUserUI();
    void showProgressLogin();
    void hideProgressLogin();
    void loginMainUI(User user);
}
