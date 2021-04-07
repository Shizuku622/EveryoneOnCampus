package com.android.everyoneoncampus.view.forgetpasswd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityForgetPasswdBinding;

public class ForgetPasswdActivity extends BaseActivity {
    private ActivityForgetPasswdBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityForgetPasswdBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
        initListener();
    }

    private void initView() {

    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            finish();
        });
    }
}