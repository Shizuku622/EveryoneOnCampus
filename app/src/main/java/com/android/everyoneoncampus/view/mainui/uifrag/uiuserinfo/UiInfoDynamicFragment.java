package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.databinding.FragmentUiInfoDynamicBinding;

public class UiInfoDynamicFragment extends Fragment {
    private FragmentUiInfoDynamicBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiInfoDynamicBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        return view;
    }
}
