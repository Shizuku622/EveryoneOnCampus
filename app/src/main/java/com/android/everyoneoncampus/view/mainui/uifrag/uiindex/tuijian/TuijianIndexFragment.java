package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.FragmentIndexTuijianBinding;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.presenter.Presenter;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class TuijianIndexFragment extends Fragment {
    private FragmentIndexTuijianBinding mBinding;
    private List<Things> mThingsList = new ArrayList<>();
    private TuiJianAdapter mAdapter;
    private Presenter mPresenter = new Presenter(this);
    private static final String TAG = "TuijianIndexFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIndexTuijianBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        Log.d(TAG, "重新启动");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        mPresenter.getThingsAll();
    }

    private void initListener() {
        mBinding.swpTuijian.setOnRefreshListener(()->{
            mPresenter.getThingsAll();
        });

    }

    private void initView() {
        mAdapter = new TuiJianAdapter(mThingsList,getActivity());
        mBinding.recThings.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mBinding.recThings.setLayoutManager(manager);

        //设置轮播图
        List<Integer> picId = new ArrayList<>();
        picId.add(R.drawable.eoc_banner_pic);
        picId.add(R.drawable.eoc_banner_pic2);
        picId.add(R.drawable.eoc_banner_pic3);
        //banner 适配器
        mBinding.bannerIndexPic.setAdapter(new BannerImageAdapter<Integer>(picId){
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
            }
        });
        mBinding.bannerIndexPic.setOnBannerListener(new OnBannerListener<Integer>() {
            @Override
            public void OnBannerClick(Integer data, int position) {
                switch(data){
                    case R.drawable.eoc_banner_pic:
                        Toast.makeText(TuijianIndexFragment.this.getActivity(), "点击了pic图片", Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.eoc_banner_pic2:
                        Toast.makeText(TuijianIndexFragment.this.getActivity(), "点击了pic2图片", Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.eoc_banner_pic3:
                        Toast.makeText(TuijianIndexFragment.this.getActivity(), "点击了pic3图片", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }


    public void refreshTuijian(List<Things> list){
        mThingsList.clear();
        mThingsList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    public void stopRefresh(){
        mBinding.swpTuijian.setRefreshing(false);
    }
    public void startRefresh(){
        mBinding.swpTuijian.setRefreshing(true);
    }

}
