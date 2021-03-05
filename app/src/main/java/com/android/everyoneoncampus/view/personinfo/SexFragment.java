package com.android.everyoneoncampus.view.personinfo;

import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentSexBinding;
import com.android.everyoneoncampus.model.SPModel;

public class SexFragment extends Fragment {
    private FragmentSexBinding mbinding;
    private SPModel mSPModel = new SPModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mbinding = FragmentSexBinding.inflate(inflater,container,false);
        View view = mbinding.getRoot();

        mbinding.llayoutNan.setOnClickListener(v->{
            mbinding.llayoutNan.setBackgroundResource(R.drawable.select_info);
            mbinding.llayoutNv.setBackgroundResource(R.drawable.select_sex_no);
            mSPModel.writeSex("男");
        });
        mbinding.imgbtnNv.setOnClickListener(v->{
            mbinding.llayoutNan.setBackgroundResource(R.drawable.select_sex_no);
            mbinding.llayoutNv.setBackgroundResource(R.drawable.select_info);
            mSPModel.writeSex("女");
        });

        return view;
    }




}
