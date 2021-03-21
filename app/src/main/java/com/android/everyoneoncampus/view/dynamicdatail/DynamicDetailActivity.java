package com.android.everyoneoncampus.view.dynamicdatail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityDynamicDetailBinding;

public class DynamicDetailActivity extends AppCompatActivity {
    private ActivityDynamicDetailBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDynamicDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();

    }

    private void initView() {
    }
}