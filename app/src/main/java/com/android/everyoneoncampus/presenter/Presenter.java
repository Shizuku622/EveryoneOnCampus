package com.android.everyoneoncampus.presenter;

import android.app.Activity;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.ThingsConvert;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.model.api.SPModel;
import com.android.everyoneoncampus.view.mainui.ReleaseDynamicActivity;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.TuijianIndexFragment;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class Presenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private SPModel mSPModel = new SPModel();
    private DbHelper mDbHelper = new DbHelper();
    private TuijianIndexFragment mTuijianIndexFragmentView;
    private ReleaseDynamicActivity mReleaseDynamicActivity;

    public Presenter(Fragment fragment){
        mTuijianIndexFragmentView = (TuijianIndexFragment)fragment;
    }

    public Presenter(Activity activity){
        mReleaseDynamicActivity = (ReleaseDynamicActivity)activity;
    }


    //获得新鲜事
    public void getThingsAll(int getWhere){
        //先从sqlite 获取事件

        mTuijianIndexFragmentView.startRefresh();
        if(mDbHelper.selectThingsCount() != 0 && getWhere == 1){
            mDbHelper.getSQLiteAllThings(new DataListener<List<Things>>() {
                @Override
                public void onComplete(List<Things> result) {
                    mTuijianIndexFragmentView.updateTuijian(result);
                    mTuijianIndexFragmentView.stopRefresh();
                }
            });
        }else{
            mMySQLModel.getThingsApi(new DataListener<List<Things>>() {
                @Override
                public void onComplete(List<Things> result) {
                    List<Things> temp = new ArrayList<>();
                    for(Things things : result){
                        if(!things.event.equals("丢失") && !things.event.equals("拾到")){
                            temp.add(things);
                        }
                    }
                    mDbHelper.saveSQLiteThings(temp);
                    mTuijianIndexFragmentView.updateTuijian(temp);
                    mTuijianIndexFragmentView.stopRefresh();

                }
            });
        }


    }

    //发送
    public void sendNewSomething(String t, String content, byte[] thingsImage){

        String sql1 = String.format("insert into things(userID,event,thingsContent,thingsImage) " +
                "values('%s','%s','%s',?)",mSPModel.readUserID(),ThingsConvert.convertString(t),content);
        mMySQLModel.sendNewSomethingApi(sql1, thingsImage, new DataListener<Integer>() {
            @Override
            public void onComplete(Integer result) {
                if(result == 1){
                    Toast.makeText(EocApplication.getContext(), "发送成功！", Toast.LENGTH_SHORT).show();
                    mReleaseDynamicActivity.sendSuccessFinish();
                }else{
                    Toast.makeText(EocApplication.getContext(), "发送失败，请重试！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
