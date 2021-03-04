package com.android.everyoneoncampus.leancloud;

import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

public class EocUserProvider implements LCChatProfileProvider {




    @Override
    public void fetchProfiles(List<String> userIdList, LCChatProfilesCallBack profilesCallBack) {

    }

    @Override
    public List<LCChatKitUser> getAllUsers() {
        return null;
    }
}
