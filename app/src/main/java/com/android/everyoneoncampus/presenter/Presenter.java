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
                mMySQLModel.sendNewSomethingApi("insert into things(userID,thingsContent) " +
                        "values('"+ EocApplication.getUserID() +"','"+content+"')");
                break;
            case "problem":
                mMySQLModel.sendNewSomethingApi("insert into problem(userID,problemContent) " +
                        "values('"+ EocApplication.getUserID() +"','"+content+"')");
                break;
            case "lose":
                mMySQLModel.sendNewSomethingApi("insert into lose(userID,loseContent) " +
                        "values('"+ EocApplication.getUserID() +"','"+content+"')");
                break;
            case "sign":
                mMySQLModel.sendNewSomethingApi("insert into sign(userID,signContent) " +
                        "values('"+ EocApplication.getUserID() +"','"+content+"')");
                break;
        }
    }

}
