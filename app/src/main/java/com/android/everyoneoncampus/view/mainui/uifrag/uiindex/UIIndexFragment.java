package com.android.everyoneoncampus.view.mainui.uifrag.uiindex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.everyoneoncampus.databinding.FragmentUiIndexStudyBinding;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages.GuanzhuIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages.HuatiIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages.TalkIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages.TuijianIndexFragment;

import java.util.ArrayList;
import java.util.List;

public class UIIndexFragment extends Fragment {

    private FragmentUiIndexStudyBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiIndexStudyBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] titles = {"关注","推荐","说吧","话题"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GuanzhuIndexFragment());
        fragments.add(new TuijianIndexFragment());
        fragments.add(new TalkIndexFragment());
        fragments.add(new HuatiIndexFragment());
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

    }
}
