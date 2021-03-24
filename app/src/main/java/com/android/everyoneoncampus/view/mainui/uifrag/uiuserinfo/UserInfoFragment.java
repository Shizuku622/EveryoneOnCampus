package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.everyoneoncampus.databinding.FragmentDetailInfoBinding;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.presenter.UIUserInfoPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserInfoFragment extends Fragment {
    private FragmentDetailInfoBinding mBinding;
    private UIUserInfoPresenter mUIUserInfoPresenter;
    private List<String> mLabelList = new ArrayList<>();
    private UserLableAdapter mRecAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDetailInfoBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        mUIUserInfoPresenter = new UIUserInfoPresenter(this);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        mBinding.recMyLabel.setLayoutManager(layoutManager);
        mRecAdapter = new UserLableAdapter(mLabelList);
        mBinding.recMyLabel.setAdapter(mRecAdapter);
        mUIUserInfoPresenter.getSQliteBottomDetailUserInfo();
    }

    //设置下面的用户信息
    public void setUserDetailInfo(User user){
        mBinding.txtUserinfo.setText(user.userSex+"  "+"  "+user.userPlace);
        mBinding.txtJianjie.setText(user.userAutograph);
        mBinding.txtZhuanye.setText(user.userSpeci);
        String labelString = user.userlabel;
        List<String> labelArray = new ArrayList<>();
        String[] strings = labelString.split(",");
        for(String s:strings){
            labelArray.add(s);
        }
        if(!labelArray.isEmpty()){
            mLabelList.clear();
            mLabelList.addAll(labelArray);
            mRecAdapter.notifyDataSetChanged();
        }
    }
}
