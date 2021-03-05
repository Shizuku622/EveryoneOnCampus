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

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.databinding.FragmentUiInfoIndexBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FragmentPresenter;
import com.android.everyoneoncampus.presenter.UserInfoPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UiInfoIndexFragment  extends Fragment {
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
        mUserInfoPresenter = new UserInfoPresenter(this);
        initView();
        setUserOtherInfo();
    }

    private void initView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        mBinding.recMyLabel.setLayoutManager(layoutManager);
        mRecAdapter = new MyLableAdapter(mLabelList);
        mBinding.recMyLabel.setAdapter(mRecAdapter);
    }


    //设置用户信息
    public void setUserOtherInfo(){
        mBinding.txtUserinfo.setText(EocApplication.getUserInfo().userSex+"  "+"  "+EocApplication.getUserInfo().userPlace);
        mBinding.txtJianjie.setText(EocApplication.getUserInfo().userAutograph);
        mBinding.txtZhuanye.setText(EocApplication.getUserInfo().userSpeci);
        String labelString = EocApplication.getUserInfo().userlabel;
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
