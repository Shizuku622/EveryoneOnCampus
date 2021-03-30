package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityGaodeMapBinding;

import java.util.ArrayList;
import java.util.List;

public class GaodeMapActivity extends BaseActivity {
    private ActivityGaodeMapBinding mBinding;
    private static final String TAG = "GaodeMapActivity";
    private AMap mAMap;
    private List<String> mMarkerIdList = new ArrayList<>();

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
        mapSetting(savedInstanceState);
        mBinding.gaodeTitle.setTxtTitle("学校地图");
    }

    private void mapSetting(Bundle savedInstanceState) {

        mBinding.mvGaode.onCreate(savedInstanceState);
        mAMap = mBinding.mvGaode.getMap();
        //初始化旋转 倾斜位置。
        LatLng latLng = new LatLng(39.058928,117.297806);                           //90  215
        mAMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,17.4f,60,212)));

        UiSettings mUiSettings = mAMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);    //显示指南针
        mUiSettings.setScaleControlsEnabled(true);  //显示比例
//        mUiSettings.setMyLocationButtonEnabled(true);

        Log.d(TAG, mAMap.getCameraPosition().toString());

        //同时设置以下就不行了。
//        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.interval(2000); //蓝点的刷新
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW); //蓝点的状态
//        myLocationStyle.showMyLocation(true); //显示位置按钮
//        mAMap.setMyLocationStyle(myLocationStyle);
//        mAMap.setMyLocationEnabled(true);   //显示位置


        mAMap.showIndoorMap(true);  //显示室内
        //mAMap.setMapLanguage(AMap.ENGLISH);

        //获得定位按钮
//        mAMap.setCustomMapStyle(new CustomMapStyleOptions().setEnable(true).setStyleId("a7542c98d3c1766ba447bf14deea8e2c"));


        //设置范围

//        LatLng southwestlatLng = new LatLng(39.066034,117.288697);
//        LatLng northeastlatLng = new LatLng(39.052271,117.300456);
//        LatLngBounds latLngBounds = new LatLngBounds(southwestlatLng,northeastlatLng);
//        mAMap.setMapStatusLimits(latLngBounds);
//
        LatLng ll_A = new LatLng(39.058736,117.297291);
        LatLng ll_B = new LatLng(39.059777,117.297398);
        LatLng ll_C = new LatLng(39.05922,117.298583);
        final Marker marker_a = mAMap.addMarker(new MarkerOptions().position(ll_A).title("主教学楼(A教)"));
        final Marker marker_b = mAMap.addMarker(new MarkerOptions().position(ll_B).title("第二教学楼(B教)"));
        final Marker marker_c = mAMap.addMarker(new MarkerOptions().position(ll_C).title("第三教学楼(C教)"));
        mMarkerIdList.add(marker_a.getId());
        mMarkerIdList.add(marker_b.getId());
        mMarkerIdList.add(marker_c.getId());
    }

    private void initListener() {
        mAMap.addOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //设置marker点击事件
                if(marker.getId().equals(mMarkerIdList.get(0))){
                    Intent intent = new Intent(GaodeMapActivity.this,BuildInfoActivity.class);
                    startActivity(intent);
                }
                if(marker.getId().equals(mMarkerIdList.get(1))){
                    Toast.makeText(GaodeMapActivity.this, "B教", Toast.LENGTH_SHORT).show();
                }
                if(marker.getId().equals(mMarkerIdList.get(2))){
                    Toast.makeText(GaodeMapActivity.this, "C教", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        mAMap.addOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.d(TAG, location.getLongitude() + ","+location.getLatitude());
            }
        });

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
                    case R.id.rb_map_normal:
                        mAMap.setMapType(AMap.MAP_TYPE_NORMAL);
                        break;
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
        mBinding.rbMapNormal.setChecked(true);
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