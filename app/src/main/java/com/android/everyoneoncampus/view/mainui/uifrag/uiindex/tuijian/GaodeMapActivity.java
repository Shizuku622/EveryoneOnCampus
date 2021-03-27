package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.MyLocationStyle;
import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityGaodeMapBinding;

public class GaodeMapActivity extends BaseActivity {
    private ActivityGaodeMapBinding mBinding;
    private static final String TAG = "GaodeMapActivity";
    private AMap mAMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityGaodeMapBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        //获得定位权限
        setPermission();
        initView(savedInstanceState);
        initListener();

    }

    private void setPermission() {
        String[] strPermission = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,strPermission,1);
        }
    }

    private void initView(Bundle savedInstanceState) {
        mBinding.mvGaode.onCreate(savedInstanceState);
        mAMap = mBinding.mvGaode.getMap();
        mAMap.showIndoorMap(true);

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.showMyLocation(true);
        mAMap.setMyLocationStyle(myLocationStyle);
        mAMap.setMyLocationEnabled(true);
        //获得定位按钮
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);
        mAMap.addOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.d(TAG, location.getLongitude() + ","+location.getLatitude());
            }
        });

        mBinding.gaodeTitle.setTxtTitle("学校地图");
    }

    private void initListener() {
        mBinding.gaodeTitle.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.gaodeTitle.setImgMenuOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.dlMapMenu.openDrawer(GravityCompat.END);
            }
        });

        mBinding.rgMapType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_map_nav:
                        mAMap.setMapType(AMap.MAP_TYPE_NAVI);
                        break;
                    case R.id.rb_map_night:
                        mAMap.setMapType(AMap.MAP_TYPE_NIGHT);
                        break;
                    case R.id.rb_map_satellite:
                        mAMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                        break;
                }
            }
        });
        mBinding.rbMapNav.setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.mvGaode.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBinding.mvGaode.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.mvGaode.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mBinding.mvGaode.onSaveInstanceState(outState);
    }
}