package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.databinding.ActivityUiInfoindexBinding;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.presenter.UIUserInfoPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity  {
    private ActivityUiInfoindexBinding mBinding;
    private UIUserInfoPresenter mUiUserInfoPresenter;

    private UIUserInfoFragment.Choose mChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUiInfoindexBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mUiUserInfoPresenter = new UIUserInfoPresenter(this);
        initView();
        initListener();
    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            finish();
        });
    }

    private void initView() {
        //vp和tab初始化
        String[] titles = {"主页","动态"};
        for(String title:titles){
            mBinding.tabUserinfo.addTab(mBinding.tabUserinfo.newTab().setText(title));
        }
        //添加vp的fragment
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new UserInfoFragment());
        fragments.add(new DynamicInfoFragment());
        //设置vp的适配器
        mBinding.vpUserinfo.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        //vp和tab联动
        mBinding.tabUserinfo.setupWithViewPager(mBinding.vpUserinfo,false);
        //显示 信息 或者 动态
        mChoose = (UIUserInfoFragment.Choose)getIntent().getSerializableExtra(UIUserInfoFragment.CHOOSE_USERINFO_DYNAMIC);
        if (mChoose == UIUserInfoFragment.Choose.INFO){
            mBinding.vpUserinfo.setCurrentItem(0);
        }else{
            mBinding.vpUserinfo.setCurrentItem(1);
        }
        mUiUserInfoPresenter.getSQliteTopDetailUserInfo();
    }

    public void setUserDetailInfo(User user){
        mBinding.imgHeadpic.setImageBitmap(EocTools.convertBitmap(user.headPic));
        mBinding.txtName.setText(user.userName);
    }
}