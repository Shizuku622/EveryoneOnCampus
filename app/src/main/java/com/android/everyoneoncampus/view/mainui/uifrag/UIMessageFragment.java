package com.android.everyoneoncampus.view.mainui.uifrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.databinding.FragmentUiIndexStudyBinding;
import com.android.everyoneoncampus.databinding.FragmentUiMessageBinding;

public class UIMessageFragment extends Fragment {
    private FragmentUiMessageBinding mBindg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBindg = FragmentUiMessageBinding.inflate(inflater,container,false);
        View view = mBindg.getRoot();

        return view;
    }
}
