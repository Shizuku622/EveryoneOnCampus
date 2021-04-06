package com.android.everyoneoncampus.view.library;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.databinding.ActivityLibraryBinding;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends BaseActivity {
    private ActivityLibraryBinding mBinding;
    private ChoseFloorAdapter mChoseFloorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
        initListener();
    }

    private void initView() {
        mBinding.cttLibrary.setTxtTitle("图书馆");
        mBinding.cttLibrary.showBottomView(true);
        List<String> firstFloor = new ArrayList<String>(){{
            add("科技库本书阅览室");
        }};
        List<String> secondFloor = new ArrayList<String>(){{
            add("社科库本书阅览室");
            add("电子阅览室");
        }};
        List<String> thirdFloor = new ArrayList<String>(){{
            add("职教资源阅览室");
            add("科技书借阅室A区");
            add("科技书借阅室B区");
            add("科技书借阅室C区");
        }};
        List<String> fourthFloor = new ArrayList<String>(){{
            add("社科书借阅室A区");
            add("社科书借阅室B区");
            add("社科书借阅室C区");
        }};
        List<String> fifthFloor = new ArrayList<String>(){{
            add("报刊阅览室");
        }};

        Fragment[] fragments = new Fragment[]{new FloorInfoFragment(firstFloor),new FloorInfoFragment(secondFloor),new FloorInfoFragment(thirdFloor),new FloorInfoFragment(fourthFloor),new FloorInfoFragment(fifthFloor)};

        mBinding.vpFloorInfo.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments[position];
            }

            @Override
            public int getItemCount() {
                return fragments.length;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.recChooseFloor.setLayoutManager(linearLayoutManager);
        List<String> floorName = new ArrayList<String>(){{
            add("一楼");
            add("二楼");
            add("三楼");
            add("四楼");
            add("五楼");
        }};
        mChoseFloorAdapter = new ChoseFloorAdapter(this,floorName,mBinding.recChooseFloor);
        mBinding.recChooseFloor.setAdapter(mChoseFloorAdapter);
        mChoseFloorAdapter.onClickFloor = new DataListener<Integer>() {
            @Override
            public void onComplete(Integer result) {
                if(fragments.length > result){
                    mBinding.vpFloorInfo.setCurrentItem(result);
                }
            }
        };
    }

    private void initListener() {
        mBinding.cttLibrary.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.vpFloorInfo.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mChoseFloorAdapter.setCurrentPage(position);
            }
        });
    }
}