package com.android.everyoneoncampus.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.model.Things;
import com.android.everyoneoncampus.view.mainui.ReleaseActivity;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages.TuijianIndexFragment;
import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

import java.util.List;

public class Presenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private TuijianIndexFragment mTuijianIndexFragmentView;
    private ReleaseActivity mReleaseActivity;

    public Presenter(Fragment fragment){
        mTuijianIndexFragmentView = (TuijianIndexFragment)fragment;
    }

    public Presenter(Activity activity){
        mReleaseActivity = (ReleaseActivity)activity;
    }

    //获得新鲜事
    public void getThingsAll(){
        mTuijianIndexFragmentView.startRefresh();
        mMySQLModel.getThingsAllApi(new DataListener<List<Things>>() {
            @Override
            public void onComplete(List<Things> result) {
                mTuijianIndexFragmentView.refreshTuijian(result);
                mTuijianIndexFragmentView.stopRefresh();
            }
        });
    }

    //发送
    public void sendNewSomething(String t,String content){
        switch (t){
            case "things":
                String sql1 = String.format("insert into things(userID,event,thingsContent) " +
                        "values('%s','%s','%s')",EocApplication.getUserInfo().userID,"新鲜事",content);
                mMySQLModel.sendNewSomethingApi(sql1);
                break;
            case "problem":
                String sql2 = String.format("insert into things(userID,event,thingsContent) " +
                        "values('%s','%s','%s')",EocApplication.getUserInfo().userID,"提问",content);
                mMySQLModel.sendNewSomethingApi(sql2);
                break;
            case "lose":
                String sql3 = String.format("insert into things(userID,event,thingsContent) " +
                        "values('%s','%s','%s')",EocApplication.getUserInfo().userID,"丢失",content);
                mMySQLModel.sendNewSomethingApi(sql3);
                break;
            case "sign":
                String sql4 = String.format("insert into things(userID,event,thingsContent) " +
                        "values('%s','%s','%s')",EocApplication.getUserInfo().userID,"签到",content);
                mMySQLModel.sendNewSomethingApi(sql4);
                break;
        }
    }

}
