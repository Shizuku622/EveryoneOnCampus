package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.everyoneoncampus.databinding.FragmentUiInfoIndexBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FragmentPresenter;
import com.android.everyoneoncampus.presenter.UserInfoPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UiInfoIndexFragment  extends Fragment implements UserInfo {
    private FragmentUiInfoIndexBinding mBinding;
    private UserInfoPresenter mUserInfoPresenter;
    private List<String> mLabelList = new ArrayList<>();
    private MyLableAdapter mRecAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiInfoIndexBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        mBinding.recMyLabel.setLayoutManager(layoutManager);
        mRecAdapter = new MyLableAdapter(mLabelList);
        mBinding.recMyLabel.setAdapter(mRecAdapter);
        initView();
    }

    private void initView() {
        mUserInfoPresenter = new UserInfoPresenter(this);

        mUserInfoPresenter.setUserInfo();
    }


    //设置用户信息
    @Override
    public void setUserInfo(User user){
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
