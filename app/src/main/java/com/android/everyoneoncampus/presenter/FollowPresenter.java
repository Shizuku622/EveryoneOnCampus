package com.android.everyoneoncampus.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.everyoneoncampus.CustomUserProvider;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.view.follow.FollowInfoActivity;
import com.android.everyoneoncampus.view.follow.FollowListActivity;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;

public class FollowPresenter {
    private static final String TAG = "FollowPresenter";
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
                new Thread(()->{
                    for(User user : result){
                        Bitmap bitmap = EocTools.convertByteBitmap(user.headPic);
                        String path = EocTools.saveBitmapFile(bitmap,user.userID+EocTools.HEADPIC);
                        LCChatKitUser temp = new LCChatKitUser(EocApplication.USER_MARK+user.userID,user.userName,path);
                        CustomUserProvider.addChatUser(temp);
                    }
                    Log.d(TAG, "添加完成");
                }).start();
                mFollowListActivity.refreshFollowList(result);
            }
        });
    }

    //获取我的粉丝
    public void getMyFansList(){
        mMySQLModel.getTwoFollowList(2, new DataListener<List<User>>() {
            @Override
            public void onComplete(List<User> result) {
                new Thread(()->{
                    for(User user : result){
                        Bitmap bitmap = EocTools.convertByteBitmap(user.headPic);
                        String path = EocTools.saveBitmapFile(bitmap,user.userID+EocTools.HEADPIC);
                        LCChatKitUser temp = new LCChatKitUser(EocApplication.USER_MARK+user.userID,user.userName,path);
                        CustomUserProvider.addChatUser(temp);
                    }
                }).start();
                mFollowListActivity.refreshFollowList(result);
            }
        });
    }

    //获取followinfo
    public void getFollowInfo(User user){
        mFollowInfoActivity.setFollowInfo(user);
    }

}
