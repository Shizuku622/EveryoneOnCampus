package com.android.everyoneoncampus.view.mainui.uifrag.uistudy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.everyoneoncampus.databinding.FragmentUiIndexStudyBinding;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.GuanzhuIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.HuatiIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.TalkIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.TuijianIndexFragment;

import java.util.ArrayList;
import java.util.List;

public class UIStudyFragment extends Fragment {
    private FragmentUiIndexStudyBinding mBindg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBindg = FragmentUiIndexStudyBinding.inflate(inflater,container,false);
        View view = mBindg.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] titles = {"精选","期末考试","考研","四六级","教师资格证","计算机二级",};
        List<Fragment> fragments = new ArrayList<>();
        for(int i =0;i <titles.length;i++){
            mBindg.tabIndex.addTab(mBindg.tabIndex.newTab().setText(titles[i]));
            fragments.add(new QimoKaoshiStudyFragment());
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

    }
}
