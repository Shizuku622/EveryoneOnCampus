package com.android.everyoneoncampus.presenter;

import android.graphics.MaskFilter;
import android.icu.text.AlphabeticIndex;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.allinterface.ReturnSQL;
import com.android.everyoneoncampus.model.DbHelper;
import com.android.everyoneoncampus.model.LabelAll;
import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.model.SPModel;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.model.UserModel;
import com.android.everyoneoncampus.model.UserModelInterface;
import com.android.everyoneoncampus.view.personinfo.IdentFragment;
import com.android.everyoneoncampus.view.personinfo.PersoninfoViewInterface;
import com.android.everyoneoncampus.view.register.RegisterViewInterface;
import com.android.everyoneoncampus.view.user.UserViewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.MacSpi;

public class LoginPresenter {
    //view
    private UserViewInterface mUserView ;
    private RegisterViewInterface mRegisterView;
    private PersoninfoViewInterface mPersoninfoView;

    //model
    private UserModelInterface mUserModel = new UserModel();
    //sql
    private DbHelper mDbHelper = new DbHelper();
    //sp
    private SPModel mSpModel = new SPModel();

    //MySQL data
    private MySQLModel mMySQLModel = new MySQLModel();

    public LoginPresenter(UserViewInterface userViewInterface){
        mUserView = userViewInterface;
    }
    public LoginPresenter(RegisterViewInterface registerViewInterface){
        mRegisterView = registerViewInterface;
    }
    public LoginPresenter(PersoninfoViewInterface personinfoViewInterface){
        mPersoninfoView = personinfoViewInterface;
    }
    //登录
    public void userLogin(String user,String passwd){
        mUserView.showProgressLogin();
        mMySQLModel.getUserLogin(user, passwd, new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                if(result != null){
                    if (result.userSno.equals(user)) {
                        Toast.makeText(EocApplication.getContext(), "登陆成功！", Toast.LENGTH_LONG).show();
                        mUserView.hideProgressLogin();
                        mSpModel.saveUserInfo(result.userSno);
                        String userID = result.userID;
                        EocApplication.setUserID(userID);
                        if(result.mark.equals("0")){
                            mUserView.userWriteUserInfo();
                            //清除
                            mSpModel.clearSpEditor();
                            mDbHelper.clearSelectedLabel();
                        }else{
                            mUserView.loginMainUI();
                        }
                    }else{
                        Toast.makeText(EocApplication.getContext(), "账号或者密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EocApplication.getContext(), "账号或者密码错误！", Toast.LENGTH_SHORT).show();
                }

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

    //保存数据
    public void getAllLable(){
        //获取标签
         mMySQLModel.getLabelTitle(new DataListener<Pair<List<String>, LabelAll>>() {
             @Override
             public void onComplete(Pair<List<String>, LabelAll> result) {
                 mDbHelper.insertLabelData(result.first,result.second);
             }
         });
    }

    //获取标签数据
    public void getLabelContent(){
        Pair<List<String>,LabelAll> label =  mDbHelper.readLabelData();
        List<String> labelList = mDbHelper.getSelectedLabelData();
        mPersoninfoView.setTitleContent(label.first,label.second,labelList);
    }
    //选择一个标签
    public void selectLabel(String labelName){
        mDbHelper.SelectLable(labelName);
    }

}

