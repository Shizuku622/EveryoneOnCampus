package com.android.everyoneoncampus.view.mainui.uifrag.uistudy;

import android.os.Bundle;
import android.service.quickaccesswallet.QuickAccessWalletService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.everyoneoncampus.databinding.FragmentUiIndexHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class UIStudyFragment extends Fragment {
    private FragmentUiIndexHomeBinding mBindg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBindg = FragmentUiIndexHomeBinding.inflate(inflater,container,false);
        View view = mBindg.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] titles = {"精选","期末考试","考研","四六级","教师资格证","计算机二级"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NullFragment());
        fragments.add(new QimoKaoshiStudyFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        fragments.add(new NullFragment());
        for(int i =0;i <titles.length;i++){
            mBindg.tabIndex.addTab(mBindg.tabIndex.newTab().setText(titles[i]));
        }
        mBindg.vpIndex.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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

        mBindg.tabIndex.setupWithViewPager(mBindg.vpIndex,false);
        mBindg.vpIndex.setCurrentItem(1);
    }
}
