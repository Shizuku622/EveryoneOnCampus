package com.android.everyoneoncampus.view.mainui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.databinding.ActivityAddDynamicStateBinding;

public class SendDynamicActivity extends AppCompatActivity {
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

    private void initListeners() {
        mBinding.imgExit.setOnClickListener(v->{
            finish();
        });
        mBinding.llayoutThings.setOnClickListener(v->{
            ReleaseActivity.actionActivity(this,"things");
        });
        mBinding.llayoutProblem.setOnClickListener(v->{
            ReleaseActivity.actionActivity(this,"problem");
        });
        mBinding.llayoutLose.setOnClickListener(v->{
            ReleaseActivity.actionActivity(this,"lose");
        });
        mBinding.llayoutSign.setOnClickListener(v->{
            ReleaseActivity.actionActivity(this,"sign");
        });

    }

    private void initViews() {

    }
}