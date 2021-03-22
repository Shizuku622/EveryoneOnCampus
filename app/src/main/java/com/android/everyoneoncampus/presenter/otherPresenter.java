package com.android.everyoneoncampus.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.modelapi.MySQLModel;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UserInfoFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UserInfoActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class otherPresenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private UserInfoActivity mUserInfoActivity;
    private UserInfoFragment mUserInfoFragment;

    public otherPresenter(UserInfoActivity userInfoActivity){
        mUserInfoActivity = userInfoActivity;
    }
    public otherPresenter(UserInfoFragment userInfoFragment){
        mUserInfoFragment = userInfoFragment;
    }
    //设置头像
//    public void setUserHeadPic(){
//        try {
//            //先找到本地上有没有该用户的头像，如果没有再从网上找
//            String headPicFile = EocApplication.getContext().getExternalFilesDir("").getAbsolutePath()+ File.separator+EocApplication.getUserInfo().userID+"headpic";
//            File file = new File(headPicFile);
//            if(file.exists()){
//                FileInputStream inputStream = new FileInputStream(headPicFile);
//                Bitmap bm = BitmapFactory.decodeStream(inputStream);
//                mUserInfoActivity.setHeadPic(bm);
//            }
//            else{
//                mMySQLModel.getPic(new DataListener<byte[]>() {
//                    @Override
//                    public void onComplete(byte[] result) {
//                        Bitmap headPic = EocTools.convertBitmap(result);
//                        //网上下载的图片保存到本地
//                        EocTools.saveBitmapPic(headPic);
//                        mUserInfoActivity.setHeadPic(headPic);
//                    }
//                });
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

}
