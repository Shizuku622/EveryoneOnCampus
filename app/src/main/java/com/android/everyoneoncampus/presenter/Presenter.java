package com.android.everyoneoncampus.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.model.api.SPModel;
import com.android.everyoneoncampus.view.mainui.ReleaseDynamicActivity;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.TuijianIndexFragment;

import java.util.List;

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
    public void getThingsAll(){
        //先从sqlite 获取事件
        if(mDbHelper.selectThingsCount() != 0){
            mDbHelper.getSQLiteAllThings(new DataListener<List<Things>>() {
                @Override
                public void onComplete(List<Things> result) {
                    mTuijianIndexFragmentView.updateTuijian(result);
                }
            });
        }else{
            mTuijianIndexFragmentView.startRefresh();
            mMySQLModel.getThingsApi(new DataListener<List<Things>>() {
                @Override
                public void onComplete(List<Things> result) {
                    mDbHelper.saveSQLiteThings(result);
                    mTuijianIndexFragmentView.updateTuijian(result);
                    mTuijianIndexFragmentView.stopRefresh();
                }
            });
        }


    }

    //发送
    public void sendNewSomething(String t, String content, byte[] thingsImage){
        switch (t){
            case "things":
                String sql1 = String.format("insert into things(userID,event,thingsContent,thingsImage) " +
                        "values('%s','%s','%s',?)",mSPModel.readUserID(),"新鲜事",content);
                mMySQLModel.sendNewSomethingApi(sql1,thingsImage);
                break;
            case "problem":
                String sql2 = String.format("insert into things(userID,event,thingsContent,thingsImage) " +
                        "values('%s','%s','%s',?)",mSPModel.readUserID(),"提问",content);
                mMySQLModel.sendNewSomethingApi(sql2,thingsImage);
                break;
            case "lose":
                String sql3 = String.format("insert into things(userID,event,thingsContent,thingsImage) " +
                        "values('%s','%s','%s',?)",mSPModel.readUserID(),"丢失",content);
                mMySQLModel.sendNewSomethingApi(sql3,thingsImage);
                break;
            case "sign":
                String sql4 = String.format("insert into things(userID,event,thingsContent,thingsImage) " +
                        "values('%s','%s','%s',?)",mSPModel.readUserID(),"签到",content);
                mMySQLModel.sendNewSomethingApi(sql4,thingsImage);
                break;
        }
    }

}
