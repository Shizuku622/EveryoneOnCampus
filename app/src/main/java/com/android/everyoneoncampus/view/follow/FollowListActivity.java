package com.android.everyoneoncampus.view.follow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityFollowListBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FollowPresenter;

import java.util.ArrayList;
import java.util.List;

public class FollowListActivity extends AppCompatActivity {
    private ActivityFollowListBinding mBinding;
    private FollowPresenter mFollowPresenter ;
    private FollowListItemAdapter mAdapter;
    private List<User> mUserList = new ArrayList<>();
    private int followChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFollowListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        followChoose = getIntent().getIntExtra("followchoose",0);
        mFollowPresenter = new FollowPresenter(this);
        initView();
        initListener();
        if(followChoose == 1){
            mBinding.txtFollowTitle.setText("我的关注");
            mFollowPresenter.getMyFollowList();
        }else{
            mBinding.txtFollowTitle.setText("关注我的");
            mFollowPresenter.getMyFansList();
        }
    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            finish();
        });
    }

    private void initView() {

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