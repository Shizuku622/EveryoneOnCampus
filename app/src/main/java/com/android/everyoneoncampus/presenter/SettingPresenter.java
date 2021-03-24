package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.view.setting.SettingActivity;

public class SettingPresenter {
    private SettingActivity mSettingActivity;
    private MySQLModel mMySQLModel = new MySQLModel();
    public SettingPresenter(SettingActivity settingActivity){
        mSettingActivity = settingActivity;
    }

    public void userExitLogin(){
        mMySQLModel.userExitLoginApi();
    }

}
