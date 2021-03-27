package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.databinding.ActivityGaodeMapBinding;

public class GaodeMapActivity extends BaseActivity {
    private ActivityGaodeMapBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityGaodeMapBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView(savedInstanceState);
        initListener();

    }

    private void initView(Bundle savedInstanceState) {
        mBinding.mvGaode.onCreate(savedInstanceState);
        AMap aMap = mBinding.mvGaode.getMap();



        mBinding.gaodeTitle.setTxtTitle("学校地图");
    }

    private void initListener() {
        mBinding.gaodeTitle.setImgBtnOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.mvGaode.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBinding.mvGaode.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.mvGaode.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mBinding.mvGaode.onSaveInstanceState(outState);
    }
}