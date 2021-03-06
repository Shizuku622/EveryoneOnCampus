package com.android.everyoneoncampus.view.register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.databinding.ActivityRegisterBinding;
import com.android.everyoneoncampus.presenter.LoginPresenter;
import com.android.everyoneoncampus.view.userlogin.UserLoginActivity;

public class RegisterActivity extends BaseActivity implements RegisterViewInterface{

    private ActivityRegisterBinding mBinding;
    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        setStatusBar();

        mLoginPresenter = new LoginPresenter(this);
        initListener();
    }

    private void setStatusBar(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }


    private void initListener() {
        mBinding.btnRegister.setOnClickListener(v->{
            String user = mBinding.etUser.getText().toString();
            String passwd = mBinding.etPasswd.getText().toString();
            String confirmPasswd = mBinding.etConfirmpasswd.getText().toString();
            if(passwd.equals(confirmPasswd)){
                mLoginPresenter.userRegister(user,passwd);
            }
        });
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            startActivity(new Intent(this, UserLoginActivity.class));
            finish();
        });

    }

    @Override
    public void userRegister() {
        startActivity(new Intent(this, UserLoginActivity.class));
        finish();
    }

    @Override
    public void showRegisterProgress() {
        mBinding.probarRegedit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRegisterProgress() {
        mBinding.probarRegedit.setVisibility(View.GONE);
    }
}