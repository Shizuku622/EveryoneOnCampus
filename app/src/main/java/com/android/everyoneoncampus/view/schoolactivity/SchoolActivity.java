package com.android.everyoneoncampus.view.schoolactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivitySchoolBinding;
import com.android.everyoneoncampus.model.entity.SchoolActive;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends BaseActivity {
    private ActivitySchoolBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySchoolBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
        initListener();
    }

    private void initView() {
        mBinding.cttSchoolActivity.setTxtTitle("校内活动");
        mBinding.cttSchoolActivity.showBottomView(true);
        mBinding.cttSchoolActivity.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //rec
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recSchoolActivity.setLayoutManager(layoutManager);
        List<SchoolActive> schoolActives = new ArrayList<SchoolActive>(){{
            add(new SchoolActive("活动通知","通知如下通知如下通知如下通知如下通知如下通知如下通知如下通知如下",R.drawable.eoc_school_img));
            add(new SchoolActive("活动通知","通知如下通知如下通知如下通知如下通知如下通知如下通知如下通知如下",R.drawable.eoc_school_img));
            add(new SchoolActive("活动通知","通知如下通知如下通知如下通知如下通知如下通知如下通知如下通知如下",R.drawable.eoc_school_img));
            add(new SchoolActive("活动通知","通知如下通知如下通知如下通知如下通知如下通知如下通知如下通知如下",R.drawable.eoc_school_img));
            add(new SchoolActive("活动通知","通知如下通知如下通知如下通知如下通知如下通知如下通知如下通知如下",R.drawable.eoc_school_img));
            add(new SchoolActive("活动通知","通知如下通知如下通知如下通知如下通知如下通知如下通知如下通知如下",R.drawable.eoc_school_img));
            add(new SchoolActive("活动通知","通知如下通知如下通知如下通知如下通知如下通知如下通知如下通知如下",R.drawable.eoc_school_img));
        }};
        SchoolActivityAdapter adapter = new SchoolActivityAdapter(schoolActives);
        mBinding.recSchoolActivity.setAdapter(adapter);
    }

    private void initListener() {

    }
}