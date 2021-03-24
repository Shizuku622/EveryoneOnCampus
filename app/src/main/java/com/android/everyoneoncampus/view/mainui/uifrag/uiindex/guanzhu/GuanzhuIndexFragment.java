package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.guanzhu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.everyoneoncampus.databinding.FragmentIndexGuanzhuBinding;
import com.android.everyoneoncampus.databinding.FragmentIndexTalkBinding;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.presenter.UIIndexPresenter;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.TuiJianAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuanzhuIndexFragment extends Fragment {
    private FragmentIndexGuanzhuBinding mBinding;
    private List<Things> mThingsList = new ArrayList<>();
    private UIIndexPresenter mUiIndexPresenter;
    private TuiJianAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIndexGuanzhuBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        mUiIndexPresenter = new UIIndexPresenter(this);
        initView();
        initListener();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //刷新
        mUiIndexPresenter.setThingsList();
    }

    public void stopSwipRefreash(){
        mBinding.swipGuanzhuDynamic.setRefreshing(false);
    }

    public void startSwipRefreash(){
        mBinding.swipGuanzhuDynamic.setRefreshing(true);
    }


    private void initListener() {
        mBinding.swipGuanzhuDynamic.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUiIndexPresenter.setThingsList();
                stopSwipRefreash();
            }
        });
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mBinding.recGuanzhuDynamic.setLayoutManager(layoutManager);
        mAdapter = new TuiJianAdapter(mThingsList);
        mBinding.recGuanzhuDynamic.setAdapter(mAdapter);
    }

    public void setThingsList(List<Things> list){
        mThingsList.clear();
        mThingsList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

}
