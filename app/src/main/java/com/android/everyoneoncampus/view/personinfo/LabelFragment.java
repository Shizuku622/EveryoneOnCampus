package com.android.everyoneoncampus.view.personinfo;

import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentLabelBinding;
import com.android.everyoneoncampus.model.LabelAll;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LabelFragment extends Fragment {

    private FragmentLabelBinding mBinding;

    private List<String> labelType;
    private LabelAll labelName;

    public LabelFragment(List<String> lt,LabelAll ln){
        this.labelType = lt;
        this.labelName = ln;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentLabelBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<LabelContentFragment> fragments = new ArrayList<>();
        //设置tablayout
        for(int i = 0;i < labelType.size();i++){
            mBinding.tabLabletype.addTab(mBinding.tabLabletype.newTab().setText(labelType.get(i)));
            List<String> lN = labelName.getLabelName(labelType.get(i));
            fragments.add(new LabelContentFragment(lN));
        }

        mBinding.vpLablecontent.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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
                return labelType.get(position);
            }
        });
        mBinding.tabLabletype.setupWithViewPager(mBinding.vpLablecontent,false);
    }
}
