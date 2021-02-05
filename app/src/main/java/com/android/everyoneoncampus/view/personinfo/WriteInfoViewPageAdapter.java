package com.android.everyoneoncampus.view.personinfo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class WriteInfoViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public WriteInfoViewPageAdapter(@NonNull FragmentManager fm, List<Fragment> fList) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentList = fList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
