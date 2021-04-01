package com.android.everyoneoncampus.view.mainui;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.databinding.ActivityAddDynamicStateBinding;

public class ChooseDynamicActivity extends BaseActivity {
    private ActivityAddDynamicStateBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddDynamicStateBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        initViews();
        initListeners();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void initListeners() {
        mBinding.imgExit.setOnClickListener(v->{
            finish();
        });
        mBinding.llayoutThings.setOnClickListener(v->{
            ReleaseDynamicActivity.actionActivity(this,"things");
        });
        mBinding.llayoutProblem.setOnClickListener(v->{
            ReleaseDynamicActivity.actionActivity(this,"problem");
        });
        mBinding.llayoutLose.setOnClickListener(v->{
            ReleaseDynamicActivity.actionActivity(this,"lose");
        });
        mBinding.llayoutSign.setOnClickListener(v->{
            ReleaseDynamicActivity.actionActivity(this,"take");
        });

    }

    private void initViews() {

    }
}