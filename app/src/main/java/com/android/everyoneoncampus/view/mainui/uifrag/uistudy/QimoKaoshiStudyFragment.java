package com.android.everyoneoncampus.view.mainui.uifrag.uistudy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.databinding.FragmentStudyQimokaoshiBinding;

public class QimoKaoshiStudyFragment extends Fragment {
    private FragmentStudyQimokaoshiBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentStudyQimokaoshiBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        
        return view;
    }
}
