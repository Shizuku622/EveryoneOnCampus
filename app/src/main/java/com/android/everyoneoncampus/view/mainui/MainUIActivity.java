package com.android.everyoneoncampus.view.mainui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityMainUseruiBinding;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.UIIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.UIMessageFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uistudy.UIStudyFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.UIUserInfoFragment;

public class MainUIActivity extends AppCompatActivity {
    ActivityMainUseruiBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainUseruiBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        initViews();

    }

    private void initViews() {
        UIIndexFragment uiIndexFragment = new UIIndexFragment();
        UIStudyFragment uiStudyFragment = new UIStudyFragment();
        UIMessageFragment uiMessageFragment = new UIMessageFragment();
        UIUserInfoFragment uiUserInfoFragment = new UIUserInfoFragment();
        mBinding.rgMainuiNav.setOnCheckedChangeListener((g,cid)->{
            Fragment fragment = null;
            switch (cid){
                case R.id.radiobtn_index:
                    fragment = uiIndexFragment;
                    break;
                case R.id.radiobtn_study:
                    fragment = uiStudyFragment;
                    break;
                case R.id.radiobtn_message:
                    fragment = uiMessageFragment;
                    break;
                case R.id.radiobtn_userinfo:
                    fragment = uiUserInfoFragment;
                    break;
            }
            if(fragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_frag_container,fragment).commit();
            }
        });

        mBinding.radiobtnIndex.setChecked(true);
    }


}