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
    private String mUserID = null;
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
        mUserID = intent.getStringExtra("userID");
        //获取id
        mFollowPresenter.getFollowInfo(mUserID);
    }

    private void initListener() {
        mBinding.cttFollowInfo.setImgBtnOnClickListener(v->{
            finish();
        });
        mBinding.btnFollowSendMessage.setOnClickListener(v->{
            if(mUserID != null){
                Intent intent = new Intent(FollowInfoActivity.this, LCIMConversationActivity.class);
                intent.putExtra(LCIMConstants.PEER_ID, EocApplication.USER_MARK+mUserID);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mBinding.btnFollowSendMessage.setVisibility(View.GONE);
        mBinding.cttFollowInfo.setTxtTitle("信息");
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

    public void hideLoading(){
        mBinding.rlayoutFollowInfoLoading.setVisibility(View.GONE);
    }

    public void showUserInfo(){
        mBinding.llayoutFollowInfo.setVisibility(View.VISIBLE);
    }
}