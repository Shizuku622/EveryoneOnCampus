package com.android.everyoneoncampus.view.mainui.uifrag.uiindex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.databinding.FragmentIndexGuanzhuBinding;
import com.android.everyoneoncampus.databinding.FragmentIndexTalkBinding;

public class GuanzhuIndexFragment extends Fragment {
    private FragmentIndexGuanzhuBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIndexGuanzhuBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();

        return view;
    }
}
