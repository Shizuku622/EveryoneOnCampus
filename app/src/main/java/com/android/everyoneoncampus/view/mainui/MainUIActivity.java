package com.android.everyoneoncampus.view.mainui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityMainUseruiBinding;
import com.android.everyoneoncampus.presenter.LoginPresenter;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.UIIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.UIMessageFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uistudy.UIStudyFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UIUserInfoFragment;
import com.android.everyoneoncampus.view.user.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class MainUIActivity extends AppCompatActivity {
    private ActivityMainUseruiBinding mBinding;
    private LoginPresenter mLoginPresenter;
    private static final String TAG = "MainUIActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainUseruiBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mLoginPresenter = new LoginPresenter(this);
        EocTools.setStatusBar(this);
        initViews();
        //查询机型是否一样
        mLoginPresenter.queryUserModel();
    }

    public void finishMainUI() {
        finish();
        startActivity(new Intent(this, UserActivity.class));
    }

    private void initViews() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new UIIndexFragment());
        fragmentList.add(new UIStudyFragment());
        fragmentList.add(new UIMessageFragment());
        fragmentList.add(new UIUserInfoFragment());

        //先设置ViewPager的fragment
        mBinding.vpMianUi.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        //设置事件
        mBinding.vpMianUi.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton;
                if(position>1){
                    radioButton = (RadioButton)mBinding.rgMainuiNav.getChildAt(position+1);
                }else {
                    radioButton = (RadioButton)mBinding.rgMainuiNav.getChildAt(position);
                }
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBinding.rgMainuiNav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radiobtn_index:
                        mBinding.vpMianUi.setCurrentItem(0);
                        break;
                    case R.id.radiobtn_study:
                        mBinding.vpMianUi.setCurrentItem(1);
                        break;
                    case R.id.radiobtn_add_dynamic:
                        startActivity(new Intent(MainUIActivity.this,SendDynamicActivity.class));
                        mBinding.radiobtnAddDynamic.setChecked(false);
                        Log.d(TAG, "不出现");
                        break;
                    case R.id.radiobtn_message:
                        mBinding.vpMianUi.setCurrentItem(2);
                        break;
                    case R.id.radiobtn_userinfo:
                        mBinding.vpMianUi.setCurrentItem(3);
                        break;
                }
            }
        });
        mBinding.vpMianUi.setOffscreenPageLimit(fragmentList.size());
        mBinding.radiobtnIndex.setChecked(true);
    }

}