package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.CustomUserProvider;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.LoseTake;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.LoseTakeActivity;

public class LoseTakePresenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private DbHelper mDbHelper = new DbHelper();
    private LoseTakeActivity mLoseTakeActivity;

    public LoseTakePresenter(LoseTakeActivity loseTakeActivity){
        mLoseTakeActivity = loseTakeActivity;
    }

    //保存用户信息
    public void saveLCInfo(User user){
        if(mDbHelper.selectLCUserInfo(user.userID)){
            //更新
            mDbHelper.updateLCUserInfo(user);
            CustomUserProvider.updateLCUser(user);
        }else{
            //插入
            mDbHelper.insertLCUserInfo(user);
            CustomUserProvider.addLCUser(user);
        }
    }


    //获取
    public void getLoseTakeThings(int get){
        mLoseTakeActivity.startLoading();
        mLoseTakeActivity.forbidRefresh();
        //1是主动刷新的
        if(get == 1){
            mMySQLModel.getLoseTakeThingsApi(new DataListener<LoseTake>() {
                @Override
                public void onComplete(LoseTake result) {
                    if(result.getTake().size() == 0){
                        mLoseTakeActivity.setShowTake();
                    }
                    if(result.getLose().size() == 0){
                        mLoseTakeActivity.setShowLose();
                    }
                    mLoseTakeActivity.setLoseTakeThings(result);
                    //写入到本地数据库
                    mDbHelper.saveLoseTakeThings(result);
                    mLoseTakeActivity.stopLoading();
                    mLoseTakeActivity.allowRefresh();
                }
            });
        }else{//else则是读取数据库
            mDbHelper.getLoseTakeThings(new DataListener<LoseTake>() {
                @Override
                public void onComplete(LoseTake result) {
                    if(result.getTake().size() == 0&&result.getLose().size()==0){
                        getLoseTakeThings(1);
                    }else{
                        mLoseTakeActivity.stopLoading();
                        if(result.getTake().size() == 0){
                            mLoseTakeActivity.setShowTake();
                        }
                        if(result.getLose().size() == 0){
                            mLoseTakeActivity.setShowLose();
                        }
                        mLoseTakeActivity.setLoseTakeThings(result);
                        mLoseTakeActivity.allowRefresh();
                    }
                }
            });
        }
    }

}
