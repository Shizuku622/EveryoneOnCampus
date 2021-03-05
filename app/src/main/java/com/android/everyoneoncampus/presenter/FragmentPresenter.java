package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.view.mainui.uifrag.UIUserInfoFragment;

public class FragmentPresenter {

    private UIUserInfoFragment mUiUserInfoFragment;
    private MySQLModel mMySQLModel = new MySQLModel();

    public FragmentPresenter(UIUserInfoFragment uiUserInfoFragment){
        this.mUiUserInfoFragment = uiUserInfoFragment;
    }

    public void setThreeInfo(){
        
    }



}
