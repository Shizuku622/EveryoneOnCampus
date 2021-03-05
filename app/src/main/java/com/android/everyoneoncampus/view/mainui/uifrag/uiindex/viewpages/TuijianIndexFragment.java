package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages;

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

import com.android.everyoneoncampus.databinding.FragmentIndexTuijianBinding;
import com.android.everyoneoncampus.model.Things;
import com.android.everyoneoncampus.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;


public class TuijianIndexFragment extends Fragment {
    private FragmentIndexTuijianBinding mBinding;
    private List<Things> mThingsList = new ArrayList<>();
    private TuiJianAdapter mAdapter;
    private Presenter mPresenter = new Presenter(this);
    private static final String TAG = "TuijianIndexFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIndexTuijianBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        Log.d(TAG, "重新启动");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mThingsList.add(new Things("","",""));
        mAdapter = new TuiJianAdapter(mThingsList);
        mBinding.recThings.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mBinding.recThings.setLayoutManager(manager);
        mBinding.swpTuijian.setOnRefreshListener(()->{
            mPresenter.getThingsAll();
        });
        mPresenter.getThingsAll();
    }


    public void refreshTuijian(List<Things> list){
        mThingsList.clear();
        mThingsList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    public void stopRefresh(){
        mBinding.swpTuijian.setRefreshing(false);
    }
    public void startRefresh(){
        mBinding.swpTuijian.setRefreshing(true);
    }

}
