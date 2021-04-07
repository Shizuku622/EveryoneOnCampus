package com.android.everyoneoncampus.view.userlogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.databinding.ActivityUserloginBinding;
import com.android.everyoneoncampus.presenter.LoginPresenter;
import com.android.everyoneoncampus.view.mainui.MainUIActivity;
import com.android.everyoneoncampus.view.writepersoninfo.PersoninfoActivity;
import com.android.everyoneoncampus.view.register.RegisterActivity;

import java.io.File;

public class UserLoginActivity extends BaseActivity {

    private ActivityUserloginBinding mBinding;
    private LoginPresenter mLoginPresenter;
    private static final String TAG = "UserLoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUserloginBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        //设置状态栏
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.getAllLable();
        initViews();
        initListener();
        File files = Environment.getExternalStorageDirectory().getAbsoluteFile();
        Log.d(TAG,Environment.getExternalStoragePublicDirectory("").getAbsolutePath());

        File[] twofile = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
        for(File file:twofile){
            Log.d(TAG, file.toString());
        }
    }

    private void initListener() {
        //登录
        mBinding.btnLogin.setOnClickListener(v->{
            String user = mBinding.etUser.getText().toString();
            String passwd = mBinding.etPasswd.getText().toString();
            mLoginPresenter.userLogin(user,passwd);
            InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
            if(manager != null){
                manager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        //注册按钮
        mBinding.txtRegister.setOnClickListener(v->{
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void initViews(){

    }

    public void loginMainUI(){
        //设置机型
        mLoginPresenter.setUserModel();
        finish();
        Intent intent = new Intent(UserLoginActivity.this, MainUIActivity.class);
        startActivity(intent);
        Log.d(TAG, "leancloud 登录成功！");
        Toast.makeText(EocApplication.getContext(), "登陆成功！", Toast.LENGTH_LONG).show();
    }

    public void userWriteUserInfo() {
        //获取登录用户信息是否跳转到填写信息 登录跳转
        //已mark的不用登记，没有填写要登记。
        startActivity(new Intent(this, PersoninfoActivity.class));
        finish();
    }

    public void userMainUserUI() {

    }

    public void showProgressLogin() {
        mBinding.probarLogin.setVisibility(View.VISIBLE);
    }

    public void hideProgressLogin() {
        mBinding.probarLogin.setVisibility(View.GONE);
    }
}