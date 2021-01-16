package com.android.everyoneoncampus.presenter;

import android.widget.Toast;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.model.UserModel;
import com.android.everyoneoncampus.model.UserModelInterface;
import com.android.everyoneoncampus.view.register.RegisterViewInterface;
import com.android.everyoneoncampus.view.user.UserViewInterface;

public class EocPresenter {
    //view
    private UserViewInterface mUserView ;
    private RegisterViewInterface mRegisterView;
    //model
    private UserModelInterface mUserModel = new UserModel();

    public EocPresenter(UserViewInterface userViewInterface){
        mUserView = userViewInterface;
    }
    public EocPresenter(RegisterViewInterface registerViewInterface){
        mRegisterView = registerViewInterface;
    }


    //登录
    public void userLogin(String user,String passwd){
        boolean flag =  mUserModel.userLogin(user,passwd);
        if(flag){
            mUserView.userLogin();
        }else{
            Toast.makeText(EocApplication.getContext(),"登陆失败！",Toast.LENGTH_LONG).show();
        }
    }

    //注册
    public void userRegister(String userSno,String userPasswd){
        mUserModel.userRegister(userSno,userPasswd);
        Toast.makeText(EocApplication.getContext(),"注册成功！",Toast.LENGTH_LONG).show();
        mRegisterView.userRegister();
    }


}

