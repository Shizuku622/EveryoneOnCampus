package com.android.everyoneoncampus;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.User;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

public class CustomUserProvider implements LCChatProfileProvider {

    private static CustomUserProvider customUserProvider;
    private static List<LCChatKitUser> allUser = new ArrayList<>();
    private static final String TAG = "CustomUserProvider";
    public synchronized static CustomUserProvider getInstance(){
        if(null == customUserProvider){
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    public CustomUserProvider(){

    }

    public static void addLCUser(User user){
        String userID = EocApplication.USER_MARK + user.userID;
        String userName = user.userName;
        byte[] headPicByte = user.headPic;
        //转成base64
        String headPic = Base64.encodeToString(headPicByte,Base64.DEFAULT);
        LCChatKitUser lcChatKitUser = new LCChatKitUser(userID,userName,headPic);
        allUser.add(lcChatKitUser);
        Log.d(TAG, user.userID + ","+user.userName);
    }

    public static void updateLCUser(User user){
        for(LCChatKitUser lc : allUser){
            if(lc.getUserId().equals(EocApplication.USER_MARK+user.userID)){
                lc.setName(user.userName);
                String headPic = Base64.encodeToString(user.headPic,Base64.DEFAULT);
                lc.setAvatarUrl(headPic);
                break;
            }
        }
    }

    @Override
    public void fetchProfiles(List<String> userIdList, LCChatProfilesCallBack profilesCallBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : userIdList) {
            for (LCChatKitUser user : allUser) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        profilesCallBack.done(userList, null);
    }

    @Override
    public List<LCChatKitUser> getAllUsers() {
        return allUser;
    }

}
