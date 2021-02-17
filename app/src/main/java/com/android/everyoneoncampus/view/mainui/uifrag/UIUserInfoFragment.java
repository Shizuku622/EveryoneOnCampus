package com.android.everyoneoncampus.view.mainui.uifrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.databinding.FragmentUiUserinfoBinding;

public class UIUserInfoFragment extends Fragment {
    private FragmentUiUserinfoBinding mBindg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBindg = FragmentUiUserinfoBinding.inflate(inflater,container,false);
        View view = mBindg.getRoot();

        return view;
    }
}
