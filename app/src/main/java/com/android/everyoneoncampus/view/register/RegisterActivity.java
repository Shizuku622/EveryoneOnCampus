package com.android.everyoneoncampus.view.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityRegisterBinding;
import com.android.everyoneoncampus.presenter.EocPresenter;
import com.android.everyoneoncampus.view.user.UserActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterViewInterface{

    private ActivityRegisterBinding mBinding;
    private EocPresenter mEocPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mEocPresenter = new EocPresenter(this);
        initListener();
    }

    private void initListener() {
        mBinding.btnRegister.setOnClickListener(v->{
            String user = mBinding.etUser.getText().toString();
            String passwd = mBinding.etPasswd.getText().toString();
            String confirmPasswd = mBinding.etConfirmpasswd.getText().toString();
            if(passwd.equals(confirmPasswd)){
                mEocPresenter.userRegister(user,passwd);
            }
        });
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            startActivity(new Intent(this, UserActivity.class));
            finish();
        });

    }

    @Override
    public void userRegister() {

    }
}