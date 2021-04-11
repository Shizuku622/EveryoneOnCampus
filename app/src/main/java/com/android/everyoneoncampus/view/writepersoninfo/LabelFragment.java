package com.android.everyoneoncampus.view.writepersoninfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.OperateMethod;
import com.android.everyoneoncampus.databinding.FragmentLabelBinding;
import com.android.everyoneoncampus.model.LabelAll;
import com.android.everyoneoncampus.presenter.WriteInfoPresenter;
import com.android.everyoneoncampus.view.mainui.MainUIActivity;

import java.util.ArrayList;
import java.util.List;

public class LabelFragment extends Fragment {

    private FragmentLabelBinding mBinding;

    private List<String> labelType;
    private LabelAll labelName;
    private List<String> selectedList;
    private List<LabelContentFragment> mFragments;
    private OperateMethod mOperateMethod;
    private WriteInfoPresenter mWriteInfoPresenter;
    private List<String> mSelectedList = new ArrayList<>();
    private LabelDeleteAdapter mAdapter;

    public LabelFragment(List<String> lt, LabelAll ln, List<String> sl, OperateMethod operateMethod){
        this.labelType = lt;
        this.labelName = ln;
        this.selectedList = sl;
        this.mOperateMethod = operateMethod;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentLabelBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        mWriteInfoPresenter = new WriteInfoPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragments = new ArrayList<>();
        //设置tablayout
        for(int i = 0;i < labelType.size();i++){
            mBinding.tabLabletype.addTab(mBinding.tabLabletype.newTab().setText(labelType.get(i)));
            List<String> lN = labelName.getLabelName(labelType.get(i));
            mFragments.add(new LabelContentFragment(lN, selectedList, new OperateMethod() {
                @Override
                public void onOperate(Object complete) {
                    mOperateMethod.onOperate(complete);
                    refreashNewLabel();
                }
            }));
        }

        mBinding.vpLablecontent.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
            @Override
            public int getCount() {
                return mFragments.size();
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return labelType.get(position);
            }
        });
        mBinding.tabLabletype.setupWithViewPager(mBinding.vpLablecontent,false);

        //适配删除的 recylerview
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        mBinding.recDeleteLabel.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new LabelDeleteAdapter(mSelectedList,this);
        mBinding.recDeleteLabel.setAdapter(mAdapter);

        mBinding.btnComplete.setOnClickListener(v->{
            if(mWriteInfoPresenter.infoComplete()){
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainUIActivity.class));
            }
        });

    }

    //点击之后刷新
    public void refreashNewLabel(){
        mSelectedList.clear();
        mSelectedList.addAll(mWriteInfoPresenter.getAllSelectedLabel());
        mAdapter.notifyDataSetChanged();
        mBinding.txtCount.setText(mWriteInfoPresenter.getLabelCount()+" / 6");

    }

    //点击删除{
    public void deleteLabel(String labelName){
        mWriteInfoPresenter.deleteLabel(labelName);
        mSelectedList.clear();
        mSelectedList.addAll(mWriteInfoPresenter.getAllSelectedLabel());
        mAdapter.notifyDataSetChanged();
        mBinding.txtCount.setText(mWriteInfoPresenter.getLabelCount()+" / 6");

    }




}
