package com.android.everyoneoncampus.view.mainui.uifrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentUiUserinfoBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.FragmentPresenter;
import com.bumptech.glide.Glide;

import java.io.File;

public class UIUserInfoFragment extends Fragment {
    private FragmentUiUserinfoBinding mBindg;
    private FragmentPresenter mFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBindg = FragmentUiUserinfoBinding.inflate(inflater,container,false);
        View view = mBindg.getRoot();
        mFragmentPresenter = new FragmentPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = EocApplication.EocUser;
        mBindg.txtName.setText(user.userNicheng);
        mBindg.txtJianjie.setText("简介："+user.userAutograph);


        String sex = user.userSex;
        if(sex.equals("男")){
            mBindg.imgSex.setImageResource(R.drawable.eoc_sex_man);
        }else{
            mBindg.imgSex.setImageResource(R.drawable.eoc_sex_gril);
        }
        String touxiangFilePath = getActivity().getExternalFilesDir("").getAbsoluteFile()+File.separator+"touxiang";
        File touxiang = new File(touxiangFilePath);
        if(touxiang.exists()){
            Glide.with(getActivity()).load(touxiangFilePath).into(mBindg.imgHeadpic);
        }else{
            mBindg.imgHeadpic.setImageResource(R.drawable.eoc_touxiang);
        }
    }

    public void setDynamicFollow(String dynamic,String follow,String followed){
        mBindg.txtDynamic.setText(dynamic);
        mBindg.txtFollow.setText(follow);
        mBindg.txtFollowed.setText(followed);
    }
}
