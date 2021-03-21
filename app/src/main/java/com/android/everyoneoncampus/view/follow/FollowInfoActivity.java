package com.android.everyoneoncampus.view.follow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.ChatTestActivity;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityFollowInfoBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FollowPresenter;

import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class FollowInfoActivity extends AppCompatActivity {
    private ActivityFollowInfoBinding mBinding;
    private FollowPresenter mFollowPresenter;
    private String userID = "";
    private User mListUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFollowInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFollowPresenter = new FollowPresenter(this);
        initView();
        initListener();
        String userID = getIntent().getStringExtra("follow_userID");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mListUser = (User)bundle.getSerializable(FollowListItemAdapter.GET_FOLLOW_USER_INFO);
        mFollowPresenter.getFollowInfo(mListUser);
    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            finish();
        });
        mBinding.btnFollowSendMessage.setOnClickListener(v->{
            if(!userID.isEmpty()){
                Intent intent = new Intent(FollowInfoActivity.this, LCIMConversationActivity.class);
                intent.putExtra(LCIMConstants.PEER_ID, EocApplication.USER_MARK+userID);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mBinding.btnFollowSendMessage.setVisibility(View.GONE);
    }

    public void setFollowInfo(User user){
        userID = user.userID;
        //保存图片
        Bitmap bitmap = EocTools.convertByteBitmap(user.headPic);
        EocTools.saveBitmapFile(bitmap,user.userID+EocTools.HEADPIC);



        mBinding.imgFollowHeadpic.setImageBitmap(EocTools.convertByteBitmap(user.headPic));
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