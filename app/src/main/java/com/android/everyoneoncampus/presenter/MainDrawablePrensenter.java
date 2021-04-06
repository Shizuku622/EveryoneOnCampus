package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.allinterface.DLInfo;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.User;

public class MainDrawablePrensenter {
    private MySQLModel mMySQLModel =new MySQLModel();
    private DbHelper mDbHelper = new DbHelper();
    private DLInfo mDLInfo;

    public MainDrawablePrensenter(DLInfo dlInfo){
        mDLInfo = dlInfo;
    }

    public void setDrawableInfo(){
        mDbHelper.readCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mDLInfo.setDLInfo(result);
            }
        });
    }


}
