package com.android.everyoneoncampus.view.mainui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.databinding.ActivityMainUseruiBinding;

public class MainUIActivity extends AppCompatActivity {
    ActivityMainUseruiBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainUseruiBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);



    }
}