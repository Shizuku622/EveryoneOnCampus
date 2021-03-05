package com.android.everyoneoncampus.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UiInfoIndexFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UserInfoActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UserInfoPresenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private UserInfoActivity mUserInfoActivity;
    private UiInfoIndexFragment mUiInfoIndexFragment;

    public UserInfoPresenter(UserInfoActivity userInfoActivity){
        mUserInfoActivity = userInfoActivity;
    }
    public UserInfoPresenter(UiInfoIndexFragment uiInfoIndexFragment){
        mUiInfoIndexFragment = uiInfoIndexFragment;
    }
    //设置头像
    public void setUserHeadPic(){
        try {
            //先找到本地上有没有该用户的头像，如果没有再从网上找
            String headPicFile = EocApplication.getContext().getExternalFilesDir("").getAbsolutePath()+ File.separator+EocApplication.getUserInfo().userID+"headpic";
            File file = new File(headPicFile);
            if(file.exists()){
                FileInputStream inputStream = new FileInputStream(headPicFile);
                Bitmap bm = BitmapFactory.decodeStream(inputStream);
                mUserInfoActivity.setHeadPic(bm);
            }
            else{
                mMySQLModel.getPic(new DataListener<byte[]>() {
                    @Override
                    public void onComplete(byte[] result) {
                        Bitmap headPic = EocTools.convertByteBitmap(result);
                        //网上下载的图片保存到本地
                        EocTools.saveBitmapPic(headPic);
                        mUserInfoActivity.setHeadPic(headPic);
                    }
                });
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //设置昵称
    public void setUserNicheng(){
        mMySQLModel.getCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mUserInfoActivity.setUserNicheng(result);
            }
        });
    }

    //设置用户信息
    public void setUserInfo(){
        mMySQLModel.getCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mUiInfoIndexFragment.setUserInfo(result);
            }
        });
    }



}
