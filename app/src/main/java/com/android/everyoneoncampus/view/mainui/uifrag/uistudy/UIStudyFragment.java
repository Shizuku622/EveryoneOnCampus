package com.android.everyoneoncampus.view.mainui.uifrag.uistudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.allinterface.DLInfo;
import com.android.everyoneoncampus.databinding.FragmentUiIndexHomeBinding;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.presenter.MainDrawablePrensenter;
import com.android.everyoneoncampus.view.NullFragment;
import com.android.everyoneoncampus.view.mainui.ChooseDynamicActivity;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UIUserInfoFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UserInfoActivity;
import com.android.everyoneoncampus.view.setting.SettingActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UIStudyFragment extends Fragment implements DLInfo {
    private FragmentUiIndexHomeBinding mBinding;
    private MainDrawablePrensenter mMainDrawablePrensenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiIndexHomeBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }
    @Override
    public void onPause() {
        super.onPause();
        mBinding.dlSign.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBinding.dlSign.closeDrawer(GravityCompat.START);
    }
    @Override
    public void onResume() {
        super.onResume();
        mBinding.vpIndex.setCurrentItem(1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainDrawablePrensenter = new MainDrawablePrensenter(this);
        initListener();
        initView();
    }

    private void initView() {
        String[] titles = {"精选","期末考试","考研","四六级","教师资格证","计算机二级"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NullFragment());
        fragments.add(new QimoKaoshiStudyFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        for(int i =0;i <titles.length;i++){
            mBinding.tabIndex.addTab(mBinding.tabIndex.newTab().setText(titles[i]));
        }
        mBinding.vpIndex.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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

        mBinding.tabIndex.setupWithViewPager(mBinding.vpIndex,false);
        mBinding.vpIndex.setCurrentItem(1);

        //禁止侧滑
        mBinding.dlSign.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mMainDrawablePrensenter.setDrawableInfo();
    }

    private void initListener() {
        mBinding.llayoutDlUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                intent.putExtra(UIUserInfoFragment.CHOOSE_USERINFO_DYNAMIC, UIUserInfoFragment.Choose.INFO);
                startActivity(intent);
            }
        });
        mBinding.imgDrawerMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.dlSign.openDrawer(GravityCompat.START);
            }
        });
        mBinding.llyoutDlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        mBinding.imgDlCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.dlSign.closeDrawer(GravityCompat.START);
            }
        });

        mBinding.tabIndex.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if(customView == null){
                    tab.setCustomView(R.layout.tab_custom_text);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(getActivity(),R.style.tab_txt_selected);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if(customView == null){
                    tab.setCustomView(R.layout.tab_custom_text);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(getActivity(),R.style.tab_txt_unselected);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setDLInfo(User user) {
        mBinding.txtDlNicheng.setText(user.userNicheng);
        mBinding.imgDlHeadpic.setImageBitmap(EocTools.convertBitmap(user.headPic));
    }

    @Override
    public void openDetailUserInfo() {

    }
}
