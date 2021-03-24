package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.everyoneoncampus.databinding.FragmentUiInfoDynamicBinding;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.presenter.UIUserInfoPresenter;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.TuiJianAdapter;

import java.util.ArrayList;
import java.util.List;

public class DynamicInfoFragment extends Fragment {
    private FragmentUiInfoDynamicBinding mBinding;
    private final List<Things> mThingList = new ArrayList<>();
    private UIUserInfoPresenter mUIUserInfoPresenter;
    private TuiJianAdapter mAdapter;
    private static final String TAG = "DynamicInfoFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiInfoDynamicBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        mUIUserInfoPresenter = new UIUserInfoPresenter(this);
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        mBinding.swipUserDynamic.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUIUserInfoPresenter.getUserDynamic();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUIUserInfoPresenter.getUserDynamic();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mBinding.recUserDynamic.setLayoutManager(layoutManager);
        mAdapter = new TuiJianAdapter(mThingList);
        mBinding.recUserDynamic.setAdapter(mAdapter);
    }

    public void setThingList(List<Things> list){
        mThingList.clear();
        mThingList.addAll(list);
        Log.d(TAG, "加载");
        mAdapter.notifyDataSetChanged();
    }

    public void refreshDynamic(){
        mBinding.swipUserDynamic.setRefreshing(true);
    }
    public void hideRefreshDynamic(){
        mBinding.swipUserDynamic.setRefreshing(false);
    }


}
