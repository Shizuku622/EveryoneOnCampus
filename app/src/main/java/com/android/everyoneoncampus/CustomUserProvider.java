package com.android.everyoneoncampus;

import com.android.everyoneoncampus.model.modelapi.MySQLModel;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

public class CustomUserProvider implements LCChatProfileProvider {

    private static CustomUserProvider customUserProvider;
    private static List<LCChatKitUser> allUser = new ArrayList<>();
    private MySQLModel mMySQLModel = new MySQLModel();

    public synchronized static CustomUserProvider getInstance(){
        if(null == customUserProvider){
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    public CustomUserProvider(){

    }

    public static void addChatUser(LCChatKitUser lcChatKitUser){
        allUser.add(lcChatKitUser);
    }

    static{
        allUser.add(new LCChatKitUser("EOC278","林灿健",EocApplication.getContext().getExternalFilesDir("").getAbsolutePath()+277+"headpic"));
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
//            mMySQLModel.getLCChatKitUser(userId, new DataListener<LCChatKitUser>() {
//                @Override
//                public void onComplete(LCChatKitUser result) {
//                    if(result != null){
//                        userList.add(result);
//                    }
//                }
//            });
        }
        profilesCallBack.done(userList, null);
    }

    @Override
    public List<LCChatKitUser> getAllUsers() {
        return allUser;
    }

}
