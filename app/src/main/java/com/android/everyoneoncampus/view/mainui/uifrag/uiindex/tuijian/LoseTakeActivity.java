package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityLoseTakeBinding;
import com.android.everyoneoncampus.model.entity.LoseTake;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.presenter.LoseTakePresenter;

import java.util.ArrayList;
import java.util.List;

public class LoseTakeActivity extends BaseActivity {
    private ActivityLoseTakeBinding mBinding;
    private LoseTakePresenter mLoseTakePresenter;
    private List<Things> mLoseTakeThings = new ArrayList<>();
    private LoseTake mLoseTake;
    private LoseTakeAdapter mLoseTakeAdapter;
    private boolean cutLoseTake = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoseTakeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mLoseTakePresenter = new LoseTakePresenter(this);
        initView();
        initListener();
        mLoseTakePresenter.getLoseTakeThings();
    }

    private void initView() {
        mBinding.ctLosetake.showBottomView(true);
        mBinding.ctLosetake.setMenuEnable(false);
        mBinding.ctLosetake.setTxtTitle("丢失物品");

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        mBinding.recLoseTake.setLayoutManager(layoutManager1);
        mLoseTakeAdapter = new LoseTakeAdapter(mLoseTakeThings);
        mBinding.recLoseTake.setAdapter(mLoseTakeAdapter);

    }

    private void initListener() {
        mBinding.ctLosetake.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.fabLostTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoseTakeThings.clear();
                if(cutLoseTake){
                    mLoseTakeThings.addAll(mLoseTake.getLose());

                    mBinding.fabLostTake.setImageResource(R.drawable.eoc_rightexit);
                    cutLoseTake = false;
                    mBinding.ctLosetake.setTxtTitle("丢失物品");
                }else{
                    mLoseTakeThings.addAll(mLoseTake.getTake());

                    mBinding.fabLostTake.setImageResource(R.drawable.eoc_leftexit);
                    cutLoseTake = true;
                    mBinding.ctLosetake.setTxtTitle("拾到物品");
                }
                mLoseTakeAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setLoseTakeThings(LoseTake loseTake){
        mLoseTake = loseTake;
        mLoseTakeThings.clear();
        mLoseTakeThings.addAll(loseTake.getLose());
        mLoseTakeAdapter.notifyDataSetChanged();
        mBinding.fabLostTake.setVisibility(View.VISIBLE);
    }

    public void startLoading(){
        mBinding.rlayoutLoading.setVisibility(View.VISIBLE);
    }
    public void stopLoading(){
        mBinding.rlayoutLoading.setVisibility(View.GONE);
    }


}