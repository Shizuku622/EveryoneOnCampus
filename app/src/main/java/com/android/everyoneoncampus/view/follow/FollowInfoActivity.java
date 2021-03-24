package com.android.everyoneoncampus.view.follow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.CustomUserProvider;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.databinding.ActivityFollowInfoBinding;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.presenter.FollowPresenter;

import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class FollowInfoActivity extends BaseActivity {
    private ActivityFollowInfoBinding mBinding;
    private FollowPresenter mFollowPresenter;
    private User mFollowUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFollowInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFollowPresenter = new FollowPresenter(this);
        initView();
        initListener();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mFollowUser = (User)bundle.getSerializable(FollowListItemAdapter.GET_FOLLOW_USER_INFO);
        mFollowPresenter.getFollowInfo(mFollowUser);
        mFollowPresenter.saveLCInfo(mFollowUser);
    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            finish();
        });
        mBinding.btnFollowSendMessage.setOnClickListener(v->{
            if(mFollowUser != null){
                Intent intent = new Intent(FollowInfoActivity.this, LCIMConversationActivity.class);
                intent.putExtra(LCIMConstants.PEER_ID, EocApplication.USER_MARK+mFollowUser.userID);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mBinding.btnFollowSendMessage.setVisibility(View.GONE);
    }

    public void setFollowInfo(User user){
        mBinding.imgFollowHeadpic.setImageBitmap(EocTools.convertBitmap(user.headPic));
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
        mBinding.btnFollowSendMessage.setVisibility(View.VISIBLE);
    }
}