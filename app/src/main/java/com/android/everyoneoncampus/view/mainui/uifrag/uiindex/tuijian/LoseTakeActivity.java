package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.databinding.ActivityLoseTakeBinding;
import com.android.everyoneoncampus.model.entity.LoseTake;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.presenter.LoseTakePresenter;

import java.util.ArrayList;
import java.util.List;

public class LoseTakeActivity extends BaseActivity {
    private ActivityLoseTakeBinding mBinding;
    private LoseTakePresenter mLoseTakePresenter;
    private List<Things> mLoseThings = new ArrayList<>();
    private List<Things> mTakeThings = new ArrayList<>();
    private LoseTakeAdapter mLoseAdapter;
    private LoseTakeAdapter mTakeAdapter;
    private boolean cutLoseTake = false;
    private boolean mLoseInfo = false;
    private boolean mTakeInfo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoseTakeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mLoseTakePresenter = new LoseTakePresenter(this);
        initView();
        initListener();
        mLoseTakePresenter.getLoseTakeThings(2);
    }

    public void stopRefresh(){
        mBinding.swpRefreshLoseTake.setRefreshing(false);
    }

    private void initView() {
        mBinding.ctLosetake.showBottomView(true);
        mBinding.ctLosetake.setMenuEnable(false);
        mBinding.ctLosetake.setTxtTitle("丢失物品");

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        mBinding.recLose.setLayoutManager(layoutManager1);
        mBinding.recTake.setLayoutManager(layoutManager2);
        mLoseAdapter = new LoseTakeAdapter(mLoseThings, this);
        mTakeAdapter = new LoseTakeAdapter(mTakeThings,this);
        mBinding.recLose.setAdapter(mLoseAdapter);
        mBinding.recTake.setAdapter(mTakeAdapter);
    }

    private void initListener() {
        mBinding.swpRefreshLoseTake.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoseTakePresenter.getLoseTakeThings(1);
                stopRefresh();
            }
        });

        mBinding.ctLosetake.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.fabLostTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.fabLostTake.setVisibility(View.GONE);
                if(cutLoseTake){
                    mBinding.recTake.setVisibility(View.GONE);
                    mBinding.recLose.setVisibility(View.VISIBLE);
                    mBinding.fabLostTake.setImageResource(R.drawable.eoc_rightexit);
                    cutLoseTake = false;
                    mBinding.ctLosetake.setTxtTitle("丢失物品");
                    isLoseTakeEmpty();
                }else{
                    mBinding.recTake.setVisibility(View.VISIBLE);
                    mBinding.recLose.setVisibility(View.GONE);
                    mBinding.fabLostTake.setImageResource(R.drawable.eoc_leftexit);
                    cutLoseTake = true;
                    mBinding.ctLosetake.setTxtTitle("拾到物品");
                    isLoseTakeEmpty();
                }
                mBinding.fabLostTake.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setLoseTakeThings(LoseTake loseTake){
        mLoseThings.clear();
        mTakeThings.clear();
        mLoseThings.addAll(loseTake.getLose());
        mTakeThings.addAll(loseTake.getTake());
        mLoseAdapter.notifyDataSetChanged();
        mTakeAdapter.notifyDataSetChanged();
        mBinding.fabLostTake.setVisibility(View.VISIBLE);
        isLoseTakeEmpty();
    }

    public void startLoading(){
        mBinding.rlayoutLoading.setVisibility(View.VISIBLE);
    }
    public void stopLoading(){
        mBinding.rlayoutLoading.setVisibility(View.GONE);
    }

    public void setShowLose(){
        mLoseInfo = true;
    }
    public void setShowTake(){
        mTakeInfo = true;
    }

    private void showNoLose(){
        mBinding.rlayoutLoseEmpty.setVisibility(View.VISIBLE);
    }
    private void showNoTake(){
        mBinding.rlayoutTakeEmpty.setVisibility(View.VISIBLE);
    }
    private void hideNoLose(){
        mBinding.rlayoutLoseEmpty.setVisibility(View.GONE);
    }
    private void hideNoTake(){
        mBinding.rlayoutTakeEmpty.setVisibility(View.GONE);
    }

    private void isLoseTakeEmpty(){
        if(mLoseInfo && !cutLoseTake){
            showNoLose();
        }else{
            hideNoLose();
        }
        if(mTakeInfo && cutLoseTake){
            showNoTake();
        }else{
            hideNoTake();
        }
    }

    public void forbidRefresh() {
        mBinding.swpRefreshLoseTake.setEnabled(false);
    }
    public void allowRefresh(){
        mBinding.swpRefreshLoseTake.setEnabled(true);
    }
}