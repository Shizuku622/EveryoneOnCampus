package com.android.everyoneoncampus.view.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.everyoneoncampus.ActivityController;
import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.databinding.ActivitySettingBinding;
import com.android.everyoneoncampus.presenter.SettingPresenter;
import com.android.everyoneoncampus.view.userlogin.UserLoginActivity;

public class SettingActivity extends BaseActivity {
    private ActivitySettingBinding mBinding;
    private SettingPresenter mSettingPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mSettingPresenter = new SettingPresenter(this);
        initView();
        initListener();
    }

    private void initListener() {
        mBinding.cttSetting.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.btnExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "退出帐号成功", Toast.LENGTH_SHORT).show();
                mSettingPresenter.userExitLogin();
                ActivityController.finishAll();
                Intent intent = new Intent(SettingActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mBinding.cttSetting.setTxtTitle("设置");
    }
}