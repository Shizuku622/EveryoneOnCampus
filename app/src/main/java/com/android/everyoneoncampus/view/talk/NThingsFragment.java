package com.android.everyoneoncampus.view.talk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentNewthingsTalkBinding;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.presenter.NThingsProblemPresenter;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.TuiJianAdapter;

import java.util.ArrayList;
import java.util.List;

public class NThingsFragment extends Fragment {
    private FragmentNewthingsTalkBinding mBinding;
    private List<Things> mThingsList = new ArrayList<>();
    private TuiJianAdapter mTuiJianAdapter;
    private NThingsProblemPresenter mNThingsProblemPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentNewthingsTalkBinding.inflate(inflater,container,false);
        mNThingsProblemPresenter = new NThingsProblemPresenter(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        mNThingsProblemPresenter.getNewThings("新鲜事");
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.recNewthings.setLayoutManager(layoutManager);
        mTuiJianAdapter = new TuiJianAdapter(mThingsList);
        mBinding.recNewthings.setAdapter(mTuiJianAdapter);
    }

    private void initListener() {

    }

    public void setNewThingsData(List<Things> list){
        mThingsList.clear();
        mThingsList.addAll(list);
        mTuiJianAdapter.notifyDataSetChanged();
    }
}
