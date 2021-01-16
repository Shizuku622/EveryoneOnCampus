package com.android.everyoneoncampus.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.databinding.ActivityUserBinding;
import com.android.everyoneoncampus.presenter.EocPresenter;
import com.android.everyoneoncampus.view.register.RegisterActivity;

public class UserActivity extends AppCompatActivity implements UserViewInterface{

    private ActivityUserBinding mBinding;
    private EocPresenter mEocPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUserBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        //设置状态栏
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        mEocPresenter = new EocPresenter(this);
        initViews();
        initListener();
    }

    private void initListener() {
        //登录
        mBinding.btnLogin.setOnClickListener(v->{
            String user = mBinding.etUser.getText().toString();
            String passwd = mBinding.etPasswd.getText().toString();
            mEocPresenter.userLogin(user,passwd);
        });

        //注册按钮
        mBinding.txtRegister.setOnClickListener(v->{
            startActivity(new Intent(this, RegisterActivity.class));
        });

    }

    private void initViews(){
        //登录
    }

    @Override
    public void userLogin() {
        //登录跳转
        startActivity(new Intent());
    }
}