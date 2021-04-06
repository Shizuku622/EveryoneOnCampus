package com.android.everyoneoncampus.view.talk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.everyoneoncampus.databinding.FragmentIndexTalkBinding;

public class TalkIndexFragment extends Fragment {
    private FragmentIndexTalkBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIndexTalkBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] titles = {"新鲜事","提问"};
        Fragment[] fragments = {new NThingsFragment(),new ProblemFragment()};
        for(String t : titles){
            mBinding.tabTalk.addTab(mBinding.tabTalk.newTab().setText(t));
        }

        mBinding.vpTalk.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }
            @Override
            public int getCount() {
                return fragments.length;
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mBinding.tabTalk.setupWithViewPager(mBinding.vpTalk);
        mBinding.vpTalk.setOffscreenPageLimit(fragments.length);
    }
}
