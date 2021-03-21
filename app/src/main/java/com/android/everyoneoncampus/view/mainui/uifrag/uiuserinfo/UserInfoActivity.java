package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.databinding.ActivityUiInfoindexBinding;
import com.android.everyoneoncampus.presenter.UserInfoPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity  {
    private ActivityUiInfoindexBinding mBinding;
    private UserInfoPresenter mUserInfoPresenter;
    private UIUserInfoFragment.Choose mChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUiInfoindexBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mChoose = (UIUserInfoFragment.Choose)getIntent().getSerializableExtra(UIUserInfoFragment.CHOOSE_INFO_DYNAMIC);
        initView();
        initListener();
        mUserInfoPresenter = new UserInfoPresenter(this);
        mUserInfoPresenter.setUserHeadPic();
        setUserOther();
    }

    public void setUserOther(){
        mBinding.txtName.setText(EocApplication.getUserInfo().userNicheng);
    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(v->{
            finish();
        });
    }

    private void initView() {
        String[] titles = {"主页","动态"};
        //设置标题
        for(String title:titles){
            mBinding.tabUserinfo.addTab(mBinding.tabUserinfo.newTab().setText(title));
        }
        //添加vp的fragment
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new UiInfoIndexFragment());
        fragments.add(new UiInfoDynamicFragment());
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
        if (mChoose == UIUserInfoFragment.Choose.INFO){
            mBinding.vpUserinfo.setCurrentItem(0);
        }else{
            mBinding.vpUserinfo.setCurrentItem(1);
        }
    }

    //设置头像
    public void setHeadPic(Bitmap headPic){
        mBinding.imgHeadpic.setImageBitmap(headPic);
    }

}