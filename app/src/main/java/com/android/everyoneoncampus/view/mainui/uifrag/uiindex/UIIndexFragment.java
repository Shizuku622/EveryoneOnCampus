package com.android.everyoneoncampus.view.mainui.uifrag.uiindex;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentUiIndexHomeBinding;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.guanzhu.GuanzhuIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages.TalkIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.TuijianIndexFragment;
import com.android.everyoneoncampus.view.setting.SettingActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UIIndexFragment extends Fragment {

    private FragmentUiIndexHomeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiIndexHomeBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        initListener();
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.vpIndex.setCurrentItem(1);
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

    private void initListener() {
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

    private void initView() {
        String[] titles = {"关注","推荐","说吧"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GuanzhuIndexFragment());
        fragments.add(new TuijianIndexFragment());
        fragments.add(new TalkIndexFragment());
//        fragments.add(new HuatiIndexFragment());
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
        mBinding.vpIndex.setOffscreenPageLimit(fragments.size());
        mBinding.vpIndex.setCurrentItem(1);
    }
}
