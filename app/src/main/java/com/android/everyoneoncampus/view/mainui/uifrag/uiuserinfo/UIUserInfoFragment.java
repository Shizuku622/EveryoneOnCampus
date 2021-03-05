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

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentUiUserInfoBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FragmentPresenter;
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

        mFragmentPresenter.setFollow();
        mFragmentPresenter.setDynamicInfo();
        mFragmentPresenter.getCurrentUser();
        mFragmentPresenter.setHeadPic();
        Log.d(TAG, "重建Fragment");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initListener() {
        mBinding.rlayoutInfo.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),UserInfoActivity.class);
            startActivity(intent);
        });
    }

    public void setUserInfo(User newUserInfo){
        mBinding.txtName.setText(newUserInfo.userNicheng);
        mBinding.txtJianjie.setText("简介："+newUserInfo.userAutograph);
        initListener();
        String sex = newUserInfo.userSex;
        if(sex.equals("男")){
            mBinding.imgSex.setImageResource(R.drawable.eoc_sex_man);
        }else{
            mBinding.imgSex.setImageResource(R.drawable.eoc_sex_gril);
        }
//        String touxiangFilePath = getActivity().getExternalFilesDir("").getAbsoluteFile()+File.separator+"touxiang";
//        File touxiang = new File(touxiangFilePath);
//        if(touxiang.exists()){
//            Glide.with(getActivity()).load(touxiangFilePath).into(mBinding.imgHeadpic);
//        }else{
//            mBinding.imgHeadpic.setImageResource(R.drawable.eoc_touxiang);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
