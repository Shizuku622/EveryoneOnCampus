package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentUiUserInfoBinding;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.presenter.UIUserInfoPresenter;
import com.android.everyoneoncampus.view.follow.FollowListActivity;
import com.android.everyoneoncampus.view.setting.SettingActivity;

public class UIUserInfoFragment extends Fragment {
    private FragmentUiUserInfoBinding mBinding;
    private UIUserInfoPresenter mUIUserInfoPresenter;

    public static final String CHOOSE_USERINFO_DYNAMIC = "CHOOSEUSERINFODYNAMIC";
    public static final String CHOOSE_FOLLOW = "CHOOSE_FOLLOW";


    public enum Choose {
        INFO,DYNAMIC,FOLLOW,FOLLOWED
    }

    private static final String TAG = "UIUserInfoFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiUserInfoBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        mUIUserInfoPresenter = new UIUserInfoPresenter(this);
        initView();
        initListener();
        return view;
    }

    private void initView() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUIUserInfoPresenter.getSQliteMainUserInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initListener() {
        mBinding.swipUserInfo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                mUIUserInfoPresenter.getCurrentUserInfo();
            }
        });
        mBinding.rlayoutInfo.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),UserInfoActivity.class);
            intent.putExtra(CHOOSE_USERINFO_DYNAMIC, Choose.INFO);
            startActivity(intent);
        });
        mBinding.llayoutDynamicDetail.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), UserInfoActivity.class);
            intent.putExtra(CHOOSE_USERINFO_DYNAMIC,Choose.DYNAMIC);
            getActivity().startActivity(intent);
        });

        mBinding.llayoutMyFollow.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), FollowListActivity.class);
            intent.putExtra(CHOOSE_FOLLOW,Choose.FOLLOW);
            startActivity(intent);
        });
        mBinding.llayoutFollowMe.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), FollowListActivity.class);
            intent.putExtra(CHOOSE_FOLLOW,Choose.FOLLOWED);
            startActivity(intent);
        });

        mBinding.imgbtnSetting.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            getActivity().startActivity(intent);
        });


    }

    //停止刷新
    public void stopRefresh(){
        mBinding.swipUserInfo.setRefreshing(false);
    }

    public void setUserInfo(User info){
        //性别
        String sex = info.userSex;
        if(sex.equals("男")){
            mBinding.imgSex.setImageResource(R.drawable.eoc_sex_man);
        }else{
            mBinding.imgSex.setImageResource(R.drawable.eoc_sex_gril);
        }
        //昵称
        mBinding.txtName.setText(info.userNicheng);
        //简介
        mBinding.txtJianjie.setText("简介："+info.userAutograph);
        //头像
        mBinding.imgHeadpic.setImageBitmap(EocTools.convertBitmap(info.headPic));
        if(info.dynamicNumber.equals("")){
            mBinding.txtDynamic.setText("0");

        }else{
            mBinding.txtDynamic.setText(info.dynamicNumber);
        }
        mBinding.txtFollow.setText(info.followNumber);
        mBinding.txtFollowed.setText(info.followedNumber);
    }


}
