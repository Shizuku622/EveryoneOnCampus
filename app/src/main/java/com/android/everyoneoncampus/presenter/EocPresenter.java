package com.android.everyoneoncampus.presenter;

import android.graphics.MaskFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
        //handler
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        mUserView.hideProgressLogin();
                }
            }
        };


        mUserView.showProgressLogin();
       mMySQLModel.userLogin(user, passwd, new ReturnSQL() {
           @Override
           public void onStatus(int flag) {
               if(flag == 1){
                   Toast.makeText(EocApplication.getContext(), "登陆成功！", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(EocApplication.getContext(), "登陆失败！", Toast.LENGTH_SHORT).show();
               }
               Message msg = Message.obtain();
               msg.what = 1;
               handler.sendMessage(msg);
           }
       });
    }

    //注册
    public void userRegister(String userSno,String userPasswd){
        //handler
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 1:
                        mRegisterView.hideRegisterProgress();
                }
            }
        };
        mRegisterView.showRegisterProgress();
        mMySQLModel.registerUser(userSno, userPasswd, new ReturnSQL() {
            @Override
            public void onStatus(int flag) {
                if(flag == 1){
                    Toast.makeText(EocApplication.getContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                    mRegisterView.userRegister();
                }else{
                    Toast.makeText(EocApplication.getContext(), "注册失败！", Toast.LENGTH_SHORT).show();
                }
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        });
    }
}

