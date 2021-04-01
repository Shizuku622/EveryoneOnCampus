package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.CustomUserProvider;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.view.follow.FollowInfoActivity;
import com.android.everyoneoncampus.view.follow.FollowListActivity;

import java.util.List;

public class FollowPresenter {
    private static final String TAG = "FollowPresenter";
    private MySQLModel mMySQLModel = new MySQLModel();
    private DbHelper mDbHelper = new DbHelper();
    private FollowListActivity mFollowListActivity;
    private FollowInfoActivity mFollowInfoActivity;
    public FollowPresenter(FollowListActivity followListActivity){
        mFollowListActivity = followListActivity;
    }
    public FollowPresenter(FollowInfoActivity followInfoActivity){
        mFollowInfoActivity = followInfoActivity;
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

    //获取我关注的
    public void getMyFollowList(){
        mMySQLModel.getTwoFollowList(1, new DataListener<List<User>>() {
            @Override
            public void onComplete(List<User> result) {
                mFollowListActivity.refreshFollowList(result);
            }
        });
    }

    //获取我的粉丝
    public void getMyFansList(){
        mMySQLModel.getTwoFollowList(2, new DataListener<List<User>>() {
            @Override
            public void onComplete(List<User> result) {
                mFollowListActivity.refreshFollowList(result);
            }
        });
    }

    //获取followinfo
    public void getFollowInfo(String userID){
        mMySQLModel.queryUserIDInfo(userID, new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                saveLCInfo(result);
                mFollowInfoActivity.setFollowInfo(result);
                mFollowInfoActivity.hideLoading();
                mFollowInfoActivity.showUserInfo();
            }
        });
    }

}
