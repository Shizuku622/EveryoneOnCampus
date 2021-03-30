package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityBuildInfoBinding;

public class BuildInfoActivity extends BaseActivity {
    private ActivityBuildInfoBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBuildInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
        initListener();


    }

    private void initView() {
        mBinding.titleBuildInfo.setTxtTitle("教学楼");
        mBinding.titleBuildInfo.setMenuEnable(false);
    }

    private void initListener() {
        mBinding.titleBuildInfo.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}