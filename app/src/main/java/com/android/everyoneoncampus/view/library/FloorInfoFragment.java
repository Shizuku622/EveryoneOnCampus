package com.android.everyoneoncampus.view.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.databinding.FragmentFloorAreaBinding;

import java.util.List;

public class FloorInfoFragment extends Fragment {
    private FragmentFloorAreaBinding mBinding;
    private List<String> mAreaList;

    public FloorInfoFragment(List<String> list){
        mAreaList = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentFloorAreaBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mBinding.recFloorInfo.setLayoutManager(layoutManager);
        FloorInfoAdapter adapter = new FloorInfoAdapter(mAreaList);
        mBinding.recFloorInfo.setAdapter(adapter);
    }

    private void initListener() {

    }
}
