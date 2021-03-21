package com.android.everyoneoncampus.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.databinding.ActivityLaunchBinding;
import com.android.everyoneoncampus.presenter.LoginPresenter;
import com.android.everyoneoncampus.view.mainui.MainUIActivity;
import com.android.everyoneoncampus.view.user.UserActivity;

public class LaunchActivity extends BaseActivity {
    private ActivityLaunchBinding mBinding;
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLaunchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mLoginPresenter = new LoginPresenter(this);
        initView();
    }

    private void initView() {
        //开机动画
        mBinding.imgLaunch.animate()
                .alpha(1)
                .setDuration(2000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        finish();
                        mLoginPresenter.queryLoginStauts();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    public void loginLoginUI(){
        Intent intent = new Intent(LaunchActivity.this, UserActivity.class);
        startActivity(intent);
    }
    public void LoginMainUI(){
        Intent intent = new Intent(LaunchActivity.this, MainUIActivity.class);
        startActivity(intent);
    }
}