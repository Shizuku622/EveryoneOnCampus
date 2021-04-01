package com.android.everyoneoncampus.view.follow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.databinding.ActivityFollowListBinding;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.presenter.FollowPresenter;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UIUserInfoFragment;

import java.util.ArrayList;
import java.util.List;

public class FollowListActivity extends BaseActivity {
    private ActivityFollowListBinding mBinding;
    private FollowPresenter mFollowPresenter ;
    private FollowListItemAdapter mAdapter;
    private List<User> mUserList = new ArrayList<>();
    private UIUserInfoFragment.Choose chooseFollow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFollowListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFollowPresenter = new FollowPresenter(this);
        initView();
        initListener();
    }

    private void initListener() {
        mBinding.cttFollowList.setImgBtnOnClickListener(v->{
            finish();
        });
    }

    private void initView() {
        chooseFollow = (UIUserInfoFragment.Choose)getIntent().getSerializableExtra(UIUserInfoFragment.CHOOSE_FOLLOW);
        if(chooseFollow == UIUserInfoFragment.Choose.FOLLOW){
            mBinding.cttFollowList.setTxtTitle("我的关注");
            mFollowPresenter.getMyFollowList();
        }else{
            mBinding.cttFollowList.setTxtTitle("关注我的");
            mFollowPresenter.getMyFansList();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recFollowList.setLayoutManager(layoutManager);
        mAdapter = new FollowListItemAdapter(mUserList,this);
        mBinding.recFollowList.setAdapter(mAdapter);
    }

    public void refreshFollowList(List<User> list){
        mUserList.clear();
        mUserList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

}