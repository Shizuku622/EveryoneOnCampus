package com.android.everyoneoncampus.presenter;

import android.widget.Toast;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.ReturnSQL;
import com.android.everyoneoncampus.model.MySQLModel;
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
    //MySQL API
    private MySQLModel mMySQLModel = new MySQLModel();

    public EocPresenter(UserViewInterface userViewInterface){
        mUserView = userViewInterface;
    }
    public EocPresenter(RegisterViewInterface registerViewInterface){
        mRegisterView = registerViewInterface;
    }


    //登录
    public void userLogin(String user,String passwd){
       mMySQLModel.userLogin(user, passwd, new ReturnSQL() {
           @Override
           public void onStatus(int flag) {
               if(flag == 1){
                   Toast.makeText(EocApplication.getContext(), "登陆成功！", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(EocApplication.getContext(), "登陆失败！", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    //注册
    public void userRegister(String userSno,String userPasswd){
        mMySQLModel.registerUser(userSno, userPasswd, new ReturnSQL() {
            @Override
            public void onStatus(int flag) {
                if(flag == 1){
                    Toast.makeText(EocApplication.getContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                    mRegisterView.userRegister();
                }else{
                    Toast.makeText(EocApplication.getContext(), "注册失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

