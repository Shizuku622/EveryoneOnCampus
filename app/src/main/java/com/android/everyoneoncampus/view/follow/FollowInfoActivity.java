package com.android.everyoneoncampus.view.follow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityFollowInfoBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FollowPresenter;

public class FollowInfoActivity extends AppCompatActivity {
    private ActivityFollowInfoBinding mBinding;
    private FollowPresenter mFollowPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFollowInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFollowPresenter = new FollowPresenter(this);
        initView();
        initListener();
        String userID = getIntent().getStringExtra("follow_userID");
        mFollowPresenter.getFollowInfo(userID);
    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            finish();
        });
    }

    private void initView() {

    }

    public void setFollowInfo(User user){
        mBinding.txtFollowNicheng.setText(user.userNicheng);
        mBinding.txtFollowQianming.setText(user.userAutograph);
        mBinding.txtFollowName.setText("姓名："+user.userName);
        mBinding.txtFollowSex.setText("性别："+user.userSex);
        mBinding.txtFollowSchool.setText("学校："+user.userSchool);
        mBinding.txtFollowSno.setText("学号："+user.userSno);
        mBinding.txtFollowPhone.setText("手机号："+user.userPhone);
        mBinding.txtFollowPlace.setText("地方："+user.userPlace);
        mBinding.txtFollowSp.setText("专业："+user.userSpeci);
        mBinding.txtFollowIden.setText("身份："+user.userIdentity);
    }
}