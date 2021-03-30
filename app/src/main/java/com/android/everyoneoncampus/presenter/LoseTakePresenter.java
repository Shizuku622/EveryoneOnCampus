package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.LoseTake;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian.LoseTakeActivity;

public class LoseTakePresenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private LoseTakeActivity mLoseTakeActivity;

    public LoseTakePresenter(LoseTakeActivity loseTakeActivity){
        mLoseTakeActivity = loseTakeActivity;
    }

    public void getLoseTakeThings(){
        mLoseTakeActivity.startLoading();
        mMySQLModel.getLoseTakeThingsApi(new DataListener<LoseTake>() {
            @Override
            public void onComplete(LoseTake result) {
                mLoseTakeActivity.setLoseTakeThings(result);
                mLoseTakeActivity.stopLoading();
            }
        });
    }

}
