package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.view.follow.FollowInfoActivity;
import com.android.everyoneoncampus.view.follow.FollowListActivity;

import java.util.List;

public class FollowPresenter {

    private MySQLModel mMySQLModel = new MySQLModel();
    private FollowListActivity mFollowListActivity;
    private FollowInfoActivity mFollowInfoActivity;
    public FollowPresenter(FollowListActivity followListActivity){
        mFollowListActivity = followListActivity;
    }
    public FollowPresenter(FollowInfoActivity followInfoActivity){
        mFollowInfoActivity = followInfoActivity;
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
        mMySQLModel.getFollowInfo(userID, new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mFollowInfoActivity.setFollowInfo(result);
            }
        });
    }


}
