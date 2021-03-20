package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentUiUserInfoBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FragmentPresenter;
import com.android.everyoneoncampus.view.follow.FollowListActivity;
import com.bumptech.glide.Glide;

import java.io.File;
import java.time.LocalTime;

public class UIUserInfoFragment extends Fragment {
    private FragmentUiUserInfoBinding mBinding;
    private FragmentPresenter mFragmentPresenter;
    private static final String TAG = "UIUserInfoFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiUserInfoBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        mFragmentPresenter = new FragmentPresenter(this);
        setUserOtherInfo();
        mFragmentPresenter.setHeadPic();
        mFragmentPresenter.setFollow();
        mFragmentPresenter.setDynamicInfo();
        Log.d(TAG, "重建Fragment");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        setUserOtherInfo();
        mBinding.swipUserInfo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mFragmentPresenter.setFollow();
                mFragmentPresenter.setDynamicInfo();
                mFragmentPresenter.getCurrentUserUpdate();
                mFragmentPresenter.setHeadPic();
            }
        });
    }

    //设置信息
    public void setUserOtherInfo(){
        mBinding.txtName.setText(EocApplication.getUserInfo().userName);
        mBinding.txtJianjie.setText("简介："+EocApplication.getUserInfo().userAutograph);
        String sex = EocApplication.getUserInfo().userSex;
        if(sex.equals("男")){
            mBinding.imgSex.setImageResource(R.drawable.eoc_sex_man);
        }else{
            mBinding.imgSex.setImageResource(R.drawable.eoc_sex_gril);
        }
    }

    //停止刷新
    public void stopRefresh(){
        mBinding.swipUserInfo.setRefreshing(false);
    }


    private void initListener() {
        mBinding.rlayoutInfo.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),UserInfoActivity.class);
            startActivity(intent);
        });
        mBinding.llayoutMyFollow.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), FollowListActivity.class);
            intent.putExtra("followchoose",1);
            startActivity(intent);
        });
        mBinding.llayoutFollowMe.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), FollowListActivity.class);
            intent.putExtra("followchoose",2);
            startActivity(intent);
        });
    }


    public void setDynamic(String dynamic){
        mBinding.txtDynamic.setText(dynamic);
    }
    public void setFollow(String follow){
        mBinding.txtFollow.setText(follow);
    }
    public void setFollowed(String followed){
        mBinding.txtFollowed.setText(followed);
    }

    //设置头像
    public void setHeadPic(Bitmap bitmap){
        mBinding.imgHeadpic.setImageBitmap(bitmap);
    }


}
