package com.android.everyoneoncampus.view.mainui.uifrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentUiMessageBinding;

import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;

public class UIMessageFragment extends Fragment {
    private FragmentUiMessageBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUiMessageBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flayout_conversation,new LCIMConversationListFragment());
        fragmentTransaction.commit();
    }
}
