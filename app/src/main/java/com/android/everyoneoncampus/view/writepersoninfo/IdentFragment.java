package com.android.everyoneoncampus.view.writepersoninfo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentIdenBinding;
import com.android.everyoneoncampus.model.api.SPModel;

public class IdentFragment  extends Fragment {
    private FragmentIdenBinding mbinding;
    private SPModel mSPModel = new SPModel();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mbinding = FragmentIdenBinding.inflate(inflater,container,false);
        View view = mbinding.getRoot();

        mbinding.rlayoutStudent.setOnClickListener(v->{
            mbinding.rlayoutStudent.setBackgroundResource(R.drawable.select_ident);
            mbinding.txtStudent.setTextColor(Color.parseColor("#ffffff"));

            mbinding.rlayoutTeacher.setBackgroundResource(R.drawable.layout_border);
            mbinding.txtTeacher.setTextColor(Color.parseColor("#000000"));
//            mSPModel.writeIdent("学生");
            EocApplication.getUserInfo().userIdentity = "学生";
        });
        mbinding.rlayoutTeacher.setOnClickListener(v->{
            mbinding.rlayoutTeacher.setBackgroundResource(R.drawable.select_ident);
            mbinding.txtTeacher.setTextColor(Color.parseColor("#ffffff"));

            mbinding.rlayoutStudent.setBackgroundResource(R.drawable.layout_border);
            mbinding.txtStudent.setTextColor(Color.parseColor("#000000"));
//            mSPModel.writeIdent("老师");
            EocApplication.getUserInfo().userIdentity = "老师";
        });


        return view;
    }



}
